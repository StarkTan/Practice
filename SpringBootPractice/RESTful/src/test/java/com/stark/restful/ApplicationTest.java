package com.stark.restful;

import com.stark.restful.entity.BaseResponse;
import com.stark.restful.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(JUnit4.class)
public class ApplicationTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testUserController() throws Exception {
        RestTemplate template = new RestTemplate();
        final String url_poxi = "http://localhost:8080";
        // 1、get查一下user列表，应该为空
        BaseResponse<List<User>> response1 = template.getForObject(url_poxi + "/users/", BaseResponse.class);
        assertNotNull(response1);
        assertTrue(response1.isSuccess());
        assertEquals("查询列表成功", response1.getMsg());
        assertThat(response1.getData().size(), is(0));
        // 2、post提交一个user
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("id", "1");
        map.add("name","测试大师");
        map.add("age","20");
        BaseResponse<String> response2 = template.postForObject(url_poxi + "/users/", map, BaseResponse.class);
        assertNotNull(response2);
        assertThat(response2.isSuccess(), is(true));
        assertThat(response2.getMsg(), is("新增成功"));
        // 3、get获取user列表，应该有刚才插入的数据
        BaseResponse<List<User>> response3 = template.getForObject(url_poxi + "/users/", BaseResponse.class);
        assertNotNull(response3);
        assertTrue(response3.isSuccess());
        assertEquals("查询列表成功", response3.getMsg());
        assertThat(response3.getData().size(), is(1));
        // 4、put修改id为1的user
        map= new LinkedMultiValueMap<String, String>();
        map.add("name","终极测试大师");
        map.add("age","30");
        template.put(url_poxi + "/users/1",map);
        // 5、get一个id为1的user
        BaseResponse<HashMap> response4 = template.getForObject(url_poxi+"/users/1",BaseResponse.class);
        assertNotNull(response4);
        assertThat(response4.isSuccess(), is(true));
        assertThat(response4.getMsg(), is("查询成功"));
        HashMap user = response4.getData();
        assertThat(user.get("id"), is(1));
        assertThat(user.get("name"), is("终极测试大师"));
        // 6、del删除id为1的user
        template.delete(url_poxi+"/users/1");
        // 7、get查一下user列表，应该为空
        BaseResponse<List<User>> response5 = template.getForObject(url_poxi + "/users/", BaseResponse.class);
        assertNotNull(response5);
        assertTrue(response5.isSuccess());
        assertEquals("查询列表成功", response5.getMsg());
        assertThat(response5.getData().size(), is(0));
    }

}