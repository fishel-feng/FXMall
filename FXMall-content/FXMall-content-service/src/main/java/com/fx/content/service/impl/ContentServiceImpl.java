package com.fx.content.service.impl;

import com.fx.common.pojo.FXResult;
import com.fx.content.service.ContentService;
import com.fx.mapper.TbContentMapper;
import com.fx.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public FXResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return FXResult.ok();
    }
}
