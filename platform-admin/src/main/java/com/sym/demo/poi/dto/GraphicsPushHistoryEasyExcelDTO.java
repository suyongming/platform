package com.sym.demo.poi.dto;

/**
 * @author yongming.su
 * @version 1.0
 * @date 2020/6/16 14:26
 */

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

/**
 * @author yongming.su
 * @version 1.0
 * @date 2020/6/16 11:10
 */
@Data
public class GraphicsPushHistoryEasyExcelDTO extends BaseRowModel {
    /** 外部系统Id. */
    @ExcelProperty(value = "destinationSystemType", index = 0)
    private String destinationSystemType;

    /** 图文Id. */
    @ExcelProperty(value = "图文Id" ,index = 1)
    private String mediaId;

    /** 批次Id. */
    @ExcelProperty(value = "批次Id" ,index = 2)
    private String batchId;

    /** 消息Id. */
    @ExcelProperty(value = "消息Id" ,index = 3)
    private String msgId;

    /** 推送Id. */
    @ExcelProperty(value = "图文Id" ,index = 4)
    private String msgDataId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "图文群发日" ,index = 5,format = "yyyy-MM-dd")
    /** 图文群发日. */
    private Date createTime;

    /**
     * 功能描述:
     * 获取图文群发日.
     *
     * @methodname: getCreateTime
     * @params: []
     * @returns: java.util.Date
     * @author: somnus
     * @date: 2019-11-16 20:22:56
     */
    public Date getCreateTime() {
        return createTime == null ? null : (Date)createTime.clone();
    }

    /**
     * 功能描述:
     * 设置图文群发日.
     *
     * @methodname: setCreateTime
     * @params: [createTime：要设置的图文群发日]
     * @returns: void
     * @author: somnus
     * @date: 2019-11-16 20:23:06
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date)createTime.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GraphicsPushHistoryEasyExcelDTO that = (GraphicsPushHistoryEasyExcelDTO) o;
        return Objects.equals(msgId, that.msgId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgId);
    }
}

