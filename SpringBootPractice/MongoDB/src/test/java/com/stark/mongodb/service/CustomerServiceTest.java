package com.stark.mongodb.service;

import com.stark.mongodb.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Resource
    private CustomerService service;

    /**
     * 测试增删改查
     */
    @Test
    public void test() {

        service.deleteAll();

        // save a couple of customers
        service.save(new Customer("Alice", "Smith"));
        service.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        int count = 0;
        for (Customer customer : service.findAll()) {
            System.out.println(customer);
            count++;
        }
        assertThat(count, is(2));

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        Customer c = service.findByFirstName("Alice");
        assertThat(c, notNullValue());
        assertThat(c.getFirstName(), is("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");

        List<Customer> list = service.findByLastName("Smith");
        assertThat(list, notNullValue());
        assertThat(list.size(), greaterThan(1));
        assertThat(list.size(), is(2));
    }
}