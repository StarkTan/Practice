package com.stark.quatz.service;

import com.stark.quatz.entity.CollectionData;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    public void save(CollectionData data) {
        System.out.println("save:" + data);
    }
}
