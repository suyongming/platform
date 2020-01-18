package com.sym.modules.cv.dao;

import com.sym.modules.cv.entity.CvFaceVerifyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人脸识别对比记录表
 * 
 * @author suyongming
 * @email 476231418@qq.com
 * @date 2019-08-30 16:18:58
 */
@Mapper
public interface CvFaceVerifyDao extends BaseMapper<CvFaceVerifyEntity> {
	
}
