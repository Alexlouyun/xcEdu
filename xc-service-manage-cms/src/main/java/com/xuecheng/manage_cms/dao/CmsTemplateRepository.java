package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Alexlou on 2020/6/24.
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {
}
