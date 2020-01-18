package com.sym.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sym.common.utils.PageUtils;
import com.sym.common.utils.Query;

import com.sym.modules.order.dao.OrderMasterDao;
import com.sym.modules.order.entity.OrderMasterEntity;
import com.sym.modules.order.service.OrderMasterService;


@Service("orderMasterService")
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterDao, OrderMasterEntity> implements OrderMasterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        List<OrderMasterEntity> list = baseMapper.selectList( new QueryWrapper());

        IPage<OrderMasterEntity> page = this.page(
                new Query<OrderMasterEntity>().getPage(params),
                new QueryWrapper<OrderMasterEntity>()
        );

        return new PageUtils(page);
    }

}