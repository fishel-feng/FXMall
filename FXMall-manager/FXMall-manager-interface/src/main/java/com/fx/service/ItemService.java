package com.fx.service;

import com.fx.common.pojo.EasyUIDataGridResult;
import com.fx.common.pojo.FXResult;
import com.fx.pojo.TbItem;

public interface ItemService {

    TbItem getItemById(Long itemId);

    EasyUIDataGridResult getItemList(int page, int rows);

    FXResult addItem(TbItem item, String desc);
}
