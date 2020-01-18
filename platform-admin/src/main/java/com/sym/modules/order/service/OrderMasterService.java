package com.sym.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sym.common.utils.PageUtils;
import com.sym.modules.order.entity.OrderMasterEntity;

import java.util.Map;

/**
 * 订单主表
 *
 * @author suyongming
 * @email 476231418@qq.com
 * @date 2019-07-13 14:26:06
 */
public interface OrderMasterService extends IService<OrderMasterEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

