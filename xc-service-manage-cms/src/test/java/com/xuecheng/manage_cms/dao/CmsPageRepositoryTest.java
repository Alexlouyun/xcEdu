package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Alexlou on 2020/6/6.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    CmsPageRepository cmsPageRepository;
    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }
    @Test
    public void testFindPage(){
        //分页参数
        int page = 2;//从0开始
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }
    //修改
    @Test
    public void testUpdate() {

        //查询对象
        Optional<CmsPage> optionalCmsPage = cmsPageRepository.findById("5abefd525b05aa293098fca6");
        if (optionalCmsPage.isPresent()) {
            CmsPage cmsPage = optionalCmsPage.get();
            //设置要修改的值
            cmsPage.setPageAliase("ccc");
            //提交修改
            CmsPage save = cmsPageRepository.save(cmsPage);
            System.out.println(save);
        }
    }
     //添加
    @Test
    public void testInsert() {
          CmsPage cmsPage = new CmsPage();
          cmsPage.setPageId("s01");
          cmsPage.setPageAliase("图形界面");
          cmsPage.setPageName("测试页面");
          cmsPage.setPageCreateTime(new Date());
          List<CmsPageParam> cmsPageParams = new ArrayList<>();
          CmsPageParam cmsPageParam = new CmsPageParam();
          cmsPageParam.setPageParamName("param1");
          cmsPageParam.setPageParamValue("value1");
          cmsPageParams.add(cmsPageParam);
          cmsPage.setPageParams(cmsPageParams);
        CmsPage save = cmsPageRepository.save(cmsPage);
        System.out.println(save);
    }
    //删除
    @Test
    public void testDelete() {
        cmsPageRepository.deleteById("s01");
    }
    //根据页面名称查询
    @Test
    public void testFindByPageName() {
        CmsPage pageName = cmsPageRepository.findByPageName("index.html");
        System.out.println(pageName);
    }
    //自定义条件查询
    @Test
    public void testFindAllByExample() {

        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        cmsPage.setTemplateId("5ad9a24d68db5239b8fef199");
        cmsPage.setPageAliase("详情");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Pageable pageable = PageRequest.of(0,10);
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        System.out.println(all.getContent());
    }

    }

