package com.stark.service;

import com.stark.common.service.BaseService;
import com.stark.entity.User;
import com.stark.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User,UserRepository> {
}
