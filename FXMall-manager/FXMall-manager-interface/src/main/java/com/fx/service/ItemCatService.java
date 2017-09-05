package com.fx.service;

import com.fx.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {

    List<EasyUITreeNode> getItemCatList(long parentId);
}
