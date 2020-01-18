package com.sym.modules.cv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sym.common.utils.FaceVerifyResult;
import com.sym.common.utils.PageUtils;
import com.sym.common.utils.R;
import com.sym.modules.cv.entity.CvFaceVerifyEntity;
import com.sym.modules.cv.vo.FaceVerifyVO;
import com.sym.modules.sys.entity.SysUserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 人脸识别对比记录表
 *
 * @author suyongming
 * @email 476231418@qq.com
 * @date 2019-08-30 16:18:58
 */
public interface CvFaceVerifyService extends IService<CvFaceVerifyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R verifyFaceSave(MultipartFile multipartFile, FaceVerifyVO faceVerifyVO, SysUserEntity userEntity);
}

