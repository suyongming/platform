package com.sym.modules.poi.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 功能描述：
 * 图文结果推送至Kafka实体对象.
 *
 */
@Data
@Accessors(chain = true)
public class GraphicResultPushEasyPoiDTO {

    /** 数据来源系统标识. */
    @Excel(name = "数据来源系统标识" ,orderNum = "0")
    private String counterPartSystemType;

    /** 用户微信唯一标识. */
    @Excel(name = "用户微信唯一标识" ,orderNum = "1")
    private String customerId;

    /** 图文标识. */
    @Excel(name = "图文标识" ,orderNum = "2")
    private String mediaId;

    /** 批次Id. */
    @Excel(name = "批次Id" ,orderNum = "3")
    private String batchId;

    /** 发送标识【1-发送过；0-未发送】. */
    @Excel(name = "发送标识" ,orderNum = "4")
    private Integer isSent;

    /** 发送时状态. */
    @Excel(name = "发送时状态" ,orderNum = "5")
    private String sendingStatus;

    /** 发送后状态. */
    @Excel(name = "发送后状态" ,orderNum = "6")
    private String postSendStatus;

    /** 消息发送任务的ID. */
    @Excel(name = "消息发送任务的ID" ,orderNum = "7")
    private String msgId;

    /** 消息的数据ID. */
    @Excel(name = "消息发送任务的ID" ,orderNum = "8")
    private String msgDataId;
}
