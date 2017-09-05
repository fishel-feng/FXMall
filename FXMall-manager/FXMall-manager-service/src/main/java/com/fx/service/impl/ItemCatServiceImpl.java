package com.fx.service.impl;

import com.fx.common.pojo.EasyUITreeNode;
import com.fx.mapper.TbItemCatMapper;
import com.fx.pojo.TbItemCat;
import com.fx.pojo.TbItemCatExample;
import com.fx.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list=itemCatMapper.selectByExample(example);
        List<EasyUITreeNode> resultList=new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node=new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }
}
