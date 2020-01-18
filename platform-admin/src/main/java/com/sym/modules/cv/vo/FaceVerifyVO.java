package com.sym.modules.cv.vo;

import lombok.Data;

/**
 * @author suyongming
 * @date 创建时间：2019/8/30 15:35
 */
@Data
public class FaceVerifyVO {
    /**
     * type 0:左图 , 1 右图
     */
    private Integer type;

    /**
     * 本次操作流水编码
     * */
    private String examNo;
    //-----------
    private String username;
}
