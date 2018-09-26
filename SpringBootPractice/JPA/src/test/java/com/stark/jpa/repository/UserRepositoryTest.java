package com.stark.jpa.repository;

import com.stark.jpa.common.entity.PageRequest;
import com.stark.jpa.common.entity.PageResponse;
import com.stark.jpa.common.utils.RepositoryUtils;
import com.stark.jpa.common.utils.RepositoryUtilsImp;
import com.stark.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testExample() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            users.add(new User("name" + i % 10, 20 + i % 10, i));
        }
        List<User> users1 = repository.saveAll(users);
        System.out.println(users1.size());

        Map<String, Object[]> paramsMap = new HashMap<>();
        paramsMap.put("name_s_in", new Object[]{"name1", "name2", "name4", "name5"});
        //paramsMap.put("name_s_equal", new Object[]{"name3"});
        Map<String, String> sortMap = new HashMap<>();
        sortMap.put("num", "desc");
        /*Specification<User> userSpecification = repositoryUtils.getConditionSpec(paramsMap);
        userSpecification = userSpecification.and(repositoryUtils.getSortSpec(sortMap));

        List<User> all = repository.findAll(userSpecification);*/
        PageRequest request = new PageRequest();
        request.setPageSize(30);
        request.setPageNum(2);
        request.setParamMap(paramsMap);
        request.setSortMap(sortMap);

        PageResponse pageResponse = repository.pageQuery(request);
        System.out.println(pageResponse);

        User one = repository.findLimitOne((Specification<User>) (root, query, criteriaBuilder) -> {
            List<Order> orders = new ArrayList<>();
            orders.add(criteriaBuilder.desc(root.get("num")));
            query.orderBy(orders);
            //返回无用空条件
            return criteriaBuilder.and();
        });
        System.out.println(one.getNum());
    }
}