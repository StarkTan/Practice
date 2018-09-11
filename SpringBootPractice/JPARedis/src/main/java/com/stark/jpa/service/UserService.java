package com.stark.jpa.service;

import com.stark.jpa.entity.User;
import com.stark.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    public List<User> findAll() {
        return repository.findAll();
    }

    public void add(User user) {
        repository.save(user);
    }

    public void remove(User user) {
        System.out.println("删除用户");
        repository.delete(user);
        // 缓存存在，删除缓存
        String key = "user_" + user.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            System.out.println(("删除用户时候，从缓存中删除用户 >> " + user.getId()));
        }
    }

    public User get(long id) {
        System.out.println("获取用户start");
        // 从缓存中获取用户信息
        String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            User user = operations.get(key);
            System.out.println(("从缓存中获取了用户 id = " + id));
            return user;
        }
        User user = repository.findById(id).orElse(null);
        if (user == null) return null;
        // 插入缓存
        operations.set(key, user, 10, TimeUnit.SECONDS);
        return user;
    }
}
