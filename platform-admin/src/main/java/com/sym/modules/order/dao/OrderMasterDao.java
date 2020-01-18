package com.sym.modules.order.dao;

import com.sym.modules.order.entity.OrderMasterEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单主表
 * 
 * @author suyongming
 * @email 476231418@qq.com
 * @date 2019-07-13 14:26:06
 */
@Mapper
public interface OrderMasterDao extends BaseMapper<OrderMasterEntity> {
	
}
