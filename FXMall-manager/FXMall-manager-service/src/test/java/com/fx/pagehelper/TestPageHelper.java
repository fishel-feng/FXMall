package com.fx.pagehelper;

import com.fx.mapper.TbItemMapper;
import com.fx.pojo.TbItem;
import com.fx.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPageHelper {

    @Test
    public void testPageHelper() throws Exception {
        PageHelper.startPage(1,10);
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        TbItemMapper itemMapper=applicationContext.getBean(TbItemMapper.class);
        TbItemExample example=new TbItemExample();
//        TbItemExample.Criteria criteria=example.createCriteria();
        List<TbItem> list=itemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(list.size());
    }
}
