package com.stark.mybatis.service;

import com.stark.mybatis.entity.User;
import com.stark.mybatis.repository.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查找用户
     */
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 新增用户
     */
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 修改用户
     */
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    /**
     * 删除用户
     */
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

}
