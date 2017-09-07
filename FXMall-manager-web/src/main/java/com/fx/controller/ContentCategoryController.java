package com.fx.controller;

import com.fx.common.pojo.EasyUITreeNode;
import com.fx.common.pojo.FXResult;
import com.fx.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
        return list;
    }

    @RequestMapping("/content/category/create")
    @ResponseBody
    public FXResult addContentCategory(Long parentId,String name){
        FXResult result = contentCategoryService.addContentCategory(parentId, name);
        return result;
    }
}
