package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Alexlou on 2020/6/6.
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {

}
