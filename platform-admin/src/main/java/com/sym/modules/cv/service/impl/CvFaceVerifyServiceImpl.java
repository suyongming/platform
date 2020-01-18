package com.sym.modules.cv.service.impl;

import com.sym.common.exception.CustomException;
import com.sym.common.utils.*;
import com.sym.modules.cv.vo.FaceVerifyVO;
import com.sym.modules.oss.cloud.CloudStorageService;
import com.sym.modules.oss.cloud.OSSFactory;
import com.sym.modules.sys.entity.SysUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sym.modules.cv.dao.CvFaceVerifyDao;
import com.sym.modules.cv.entity.CvFaceVerifyEntity;
import com.sym.modules.cv.service.CvFaceVerifyService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service("cvFaceVerifyService")
@Slf4j
public class CvFaceVerifyServiceImpl extends ServiceImpl<CvFaceVerifyDao, CvFaceVerifyEntity> implements CvFaceVerifyService {

    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");

        list.removeIf(item->"2".equals(item));

        list.forEach(item->{
            System.out.println(item);
        });

        Map a = new HashMap(16);
    }
    @Autowired
    private FaceCVUtils faceCVUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CvFaceVerifyEntity> page = this.page(
                new Query<CvFaceVerifyEntity>().getPage(params),
                new QueryWrapper<CvFaceVerifyEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R verifyFaceSave(MultipartFile multipartFile, FaceVerifyVO faceVerifyVO, SysUserEntity userEntity) {
        if (multipartFile.isEmpty()) {
            throw new CustomException("上传文件不能为空");
        }
        try {
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String url = OSSFactory.build().uploadSuffix(multipartFile.getBytes(), suffix);

            List<CvFaceVerifyEntity> confList = baseMapper.selectList(new QueryWrapper<CvFaceVerifyEntity>().eq("exam_no", faceVerifyVO.getExamNo()));
            if (!CollectionUtils.isEmpty(confList) && confList.size() == 1) {
                //如果存在操作记录
                CvFaceVerifyEntity cloudFaceEntity = confList.get(0);
                cloudFaceEntity.setUsername(userEntity.getUsername());
                cloudFaceEntity.setUserId(userEntity.getUserId());

                if (faceVerifyVO.getType() == 0) {
                    cloudFaceEntity.setLNetImgPath(url);
                } else {
                    cloudFaceEntity.setRNetImgPath(url);
                }
                cloudFaceEntity.setExamNo(faceVerifyVO.getExamNo());

                if(baseMapper.updateById(cloudFaceEntity) > 0){
                    return R.ok();
                }


            } else {
                //如果不存在操作记录
                CvFaceVerifyEntity cloudFaceEntity = new CvFaceVerifyEntity();
                cloudFaceEntity.setUsername(userEntity.getUsername());
                cloudFaceEntity.setUserId(userEntity.getUserId());

                if (faceVerifyVO.getType() == 0) {
                    cloudFaceEntity.setLNetImgPath(url);
                } else {
                    cloudFaceEntity.setRNetImgPath(url);
                }
                cloudFaceEntity.setExamNo(faceVerifyVO.getExamNo());

                if(baseMapper.insert(cloudFaceEntity) > 0){
                    return R.ok();
                }
            }


        } catch (Exception e) {
            log.error("上传失败:" + userEntity.getUsername());
            throw new CustomException("上传失败");
        }


        return null;
    }

}