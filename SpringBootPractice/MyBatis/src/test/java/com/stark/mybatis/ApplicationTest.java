package com.stark.mybatis;

import com.stark.mybatis.entity.User;
import com.stark.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ApplicationTest {
    @Resource
    private UserService userService;
    /**
     * 测试增删改查
     */
    @Test
    public void test() {
        User user = new User();
        user.setUsername("xiaoxx");
        user.setName("小星星");
        user.setPassword("222222");
        user.setPhone("13890907676");
        userService.insertUser(user);

        User user1 = userService.findById(user.getId());
        assertThat(user1.getUsername(), is("xiaoxx"));
        assertThat(user1.getName(), is("小星星"));

        user1.setPassword("888888");

        userService.updateUser(user1);
        User user2 = userService.findById(user.getId());
        assertThat(user2.getPassword(), is("888888"));

        userService.deleteUser(user.getId());
        User user3 = userService.findById(user.getId());
        assertThat(user3, nullValue());

    }
}