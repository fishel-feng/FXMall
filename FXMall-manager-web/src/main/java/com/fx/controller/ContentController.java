package com.fx.controller;

import com.fx.common.pojo.FXResult;
import com.fx.content.service.ContentService;
import com.fx.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/save")
    @ResponseBody
    public FXResult addContent(TbContent content){
        FXResult result = contentService.addContent(content);
        return result;
    }
}
