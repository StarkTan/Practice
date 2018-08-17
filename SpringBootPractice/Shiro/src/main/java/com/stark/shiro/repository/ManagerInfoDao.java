package com.stark.shiro.repository;


import com.stark.shiro.common.repository.ManagerMapper;
import com.stark.shiro.entity.ManagerInfo;

/**
 * Description  :
 */
public interface ManagerInfoDao extends ManagerMapper {
    ManagerInfo findByUsername(String username);
}