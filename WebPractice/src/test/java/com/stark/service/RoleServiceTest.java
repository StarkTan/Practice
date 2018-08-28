package com.stark.service;

import com.stark.Application;
import com.stark.entity.Resource;
import com.stark.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @Test
    public void testResourceService() {
        Resource resource1 = new Resource();
        resource1.setName("resource1");
        Resource resource2 = new Resource();
        resource2.setName("resource2");
        Role role1 = new Role();
        role1.setDescription("description1");
        role1.setName("name");
        List<Resource> resources = new ArrayList<>();
        resources.add(resource1);
        resources.add(resource2);
        role1.setResources(resources);
        resource1 = resourceService.save(resource1);
        resourceService.save(resource2);
        role1 = roleService.save(role1);
        String id = role1.getId();
        assertEquals(role1.getResources().size(), 2);
        roleService.flush();
        role1.getResources().remove(resource1);
        role1 = roleService.save(role1);
        resourceService.delete(resource1);
        resourceService.flush();
        assertEquals(roleService.getById(id).getResources().size(), 1);
        roleService.delete(role1);
        assertEquals(roleService.getAll().size(), 0);

    }
}