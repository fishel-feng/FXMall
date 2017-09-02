package com.fx.service.impl;

import com.fx.mapper.TbItemMapper;
import com.fx.pojo.TbItem;
import com.fx.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        TbItem item=itemMapper.selectByPrimaryKey(itemId);
        return item;
    }
}
