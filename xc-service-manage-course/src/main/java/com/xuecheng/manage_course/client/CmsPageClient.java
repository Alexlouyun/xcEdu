package com.xuecheng.manage_course.client;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by Alexlou on 2020/6/29.
 */
@FeignClient(value = "XC-SERVICE-MANAGE-CMS")//制定远程调用的服务名
public interface CmsPageClient {
    //根据页面id查询页面信息，远程调用cms请求数据
    @GetMapping("/cms/page/get/{id}")
    public CmsPage findById(@PathVariable("id") String id);
    //保存页面
    @PostMapping("/cms/page/save")
    public CmsPageResult save(@RequestBody CmsPage cmsPage);
}
