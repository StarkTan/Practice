package com.stark.shiro.service;

import com.stark.shiro.entity.ManagerInfo;
import com.stark.shiro.exception.ForbiddenUserException;
import com.stark.shiro.repository.ManagerInfoDao;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 后台用户管理
 */

@Service
public class ManagerInfoService {

    @Resource
    private ManagerInfoDao managerInfoDao;

    /**
     * 通过名称查找用户
     */
    public ManagerInfo findByUsername(String username) {
        ManagerInfo managerInfo =  managerInfoDao.findByUsername(username);
        if (managerInfo == null) {
            throw new UnknownAccountException();
        }
        if (managerInfo.getState() == 2) {
            throw new ForbiddenUserException();
        }
        if (managerInfo.getPidsList() == null) {
            managerInfo.setPidsList(Collections.singletonList(0));
        } else if (managerInfo.getPidsList().size() == 0) {
            managerInfo.getPidsList().add(0);
        }
        return managerInfo;
    }
}