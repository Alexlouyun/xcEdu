package com.xuecheng.ucenter.dao;

import com.xuecheng.framework.domain.ucenter.XcMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Alexlou on 2020/7/10.
 */
@Mapper
public interface XcMenuMapper {
    //根据用户的id查询用户的权限
    public List<XcMenu> selectPermissionByUserId(String userId);
}
