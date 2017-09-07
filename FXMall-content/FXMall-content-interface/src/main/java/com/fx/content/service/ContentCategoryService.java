package com.fx.content.service;

import com.fx.common.pojo.EasyUITreeNode;
import com.fx.common.pojo.FXResult;

import java.util.List;

public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCategoryList(long parentId);

    FXResult addContentCategory(Long parentId,String name);
}
