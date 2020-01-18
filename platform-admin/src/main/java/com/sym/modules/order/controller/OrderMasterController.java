package com.sym.modules.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sym.modules.order.entity.OrderMasterEntity;
import com.sym.modules.order.service.OrderMasterService;
import com.sym.common.utils.PageUtils;
import com.sym.common.utils.R;



/**
 * 订单主表
 *
 * @author suyongming
 * @email 476231418@qq.com
 * @date 2019-07-13 14:26:06
 */
@RestController
@RequestMapping("order/ordermaster")
public class OrderMasterController {
    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("order:ordermaster:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderMasterService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    @RequiresPermissions("order:ordermaster:info")
    public R info(@PathVariable("orderId") Integer orderId){
		OrderMasterEntity orderMaster = orderMasterService.getById(orderId);

        return R.ok().put("orderMaster", orderMaster);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:ordermaster:save")
    public R save(@RequestBody OrderMasterEntity orderMaster){
		orderMasterService.save(orderMaster);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:ordermaster:update")
    public R update(@RequestBody OrderMasterEntity orderMaster){
		orderMasterService.updateById(orderMaster);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:ordermaster:delete")
    public R delete(@RequestBody Integer[] orderIds){
		orderMasterService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}
