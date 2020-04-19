package com.sym.modules.order.vo;

import com.sym.common.utils.PageBean;
import lombok.Data;

/**
 * @author suyongming
 * @date 创建时间：2020/4/10 23:01
 */
@Data
public class OrderListQueryVO extends PageBean {


    //收货人姓名
    private String shippingUser;

}
