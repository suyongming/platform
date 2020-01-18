package com.sym.modules.cv.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 人脸识别对比记录表
 * 
 * @author suyongming
 * @email 476231418@qq.com
 * @date 2019-08-30 16:18:58
 */
@Data
@TableName("sym_market.cv_face_verify")
public class CvFaceVerifyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 操作人id
	 */
	private Long userId;
	/**
	 * 操作人名
	 */
	private String username;
	/**
	 * 来源于https的图片（左图）
	 */
	private String lNetImgPath;
	/**
	 * 来源于https的图片（左图）
	 */
	private String rNetImgPath;
	/**
	 * 操作唯一编号
	 * */
	private String examNo;
	/**
	 * 添加时间
	 */
	private Date createTime;
	/**
	 * 修改时间(可能在做二次图片对比时更新)
	 */
	private Date modifyTime;

}
