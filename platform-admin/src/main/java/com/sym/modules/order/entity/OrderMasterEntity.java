package com.sym.modules.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单主表
 * 
 * @author suyongming
 * @email 476231418@qq.com
 * @date 2019-07-13 14:26:06
 */
@Data
@TableName("sym_market.order_master")
public class OrderMasterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单ID
	 */
	@TableId
	private Integer orderId;
	/**
	 * 订单编号 yyyymmddnnnnnnnn
	 */
	private Long orderSn;
	/**
	 * 下单人ID
	 */
	private Integer customerId;
	/**
	 * 收货人姓名
	 */
	private String shippingUser;
	/**
	 * 省
	 */
	private Integer province;
	/**
	 * 市
	 */
	private Integer city;
	/**
	 * 区
	 */
	private Integer district;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 支付方式：1现金，2余额，3网银，4支付宝，5微信
	 */
	private Integer paymentMethod;
	/**
	 * 订单金额
	 */
	private BigDecimal orderMoney;
	/**
	 * 优惠金额
	 */
	private BigDecimal districtMoney;
	/**
	 * 运费金额
	 */
	private BigDecimal shippingMoney;
	/**
	 * 支付金额
	 */
	private BigDecimal paymentMoney;
	/**
	 * 快递公司名称
	 */
	private String shippingCompName;
	/**
	 * 快递单号
	 */
	private String shippingSn;
	/**
	 * 下单时间
	 */
	private Date createTime;
	/**
	 * 发货时间
	 */
	private Date shippingTime;
	/**
	 * 支付时间
	 */
	private Date payTime;
	/**
	 * 收货时间
	 */
	private Date receiveTime;
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	/**
	 * 订单积分
	 */
	private Integer orderPoint;
	/**
	 * 发票抬头
	 */
	private String invoiceTime;
	/**
	 * 最后修改时间
	 */
	private Date modifiedTime;

}
