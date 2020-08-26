package com.xuecheng.ucenter.dao;

import com.xuecheng.framework.domain.ucenter.XcUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexlou on 2020/7/9.
 */
@Repository
public interface XcUserRepository extends JpaRepository<XcUser,String> {
    XcUser findXcUserByUsername(String username);
}

