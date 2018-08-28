package com.stark.service;

import com.stark.Application;
import com.stark.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ResourceServiceTest {

    @Autowired
    private ResourceService service;

    @Test
    public void testResourceService() {
        Resource resource1 = new Resource();
        resource1.setName("resource1");
        Resource resource2 = new Resource();
        resource2.setName("resource2");

        resource1 = service.save(resource1);
        resource2 = service.save(resource2);
        assertNotNull(resource1.getId());
        assertNotNull(resource2.getId());
        service.flush();

        assertEquals(service.getAll().size(), 2);
        service.delete(resource1);
        service.delete(resource2);
        service.flush();

        assertEquals(service.getAll().size(), 0);
    }

}