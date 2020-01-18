package com.sym.modules.cv.controller;

import com.alibaba.fastjson.JSONObject;
import com.sym.common.utils.*;
import com.sym.config.FaceRecognConfig;
import com.sym.modules.app.entity.UserEntity;
import com.sym.modules.cv.service.CvFaceVerifyService;
import com.sym.modules.cv.vo.FaceVerifyVO;
import com.sym.modules.sys.controller.AbstractController;
import com.sym.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

/**
 * @author suyongming
 * @date 创建时间：2019/8/30 14:01
 */
@RestController
@RequestMapping("cv")
public class CVManagerController extends AbstractController {
    @Autowired
    private FaceRecognConfig faceRecognConfig;

    @Autowired
    private CvFaceVerifyService cvFaceVerifyService;

    @Autowired
    private FaceCVUtils faceCVUtils;

    @GetMapping(value = "/face/test")
    public String tes(String img,String img1) throws Exception {
        String netImg = Img2Base64.encodeImageToBase64(new URL(img));
        String netImg1 = Img2Base64.encodeImageToBase64(new URL(img1));


        // 人脸检测调用
        FaceDetectResult fDetectResult = faceCVUtils.faceDetect(netImg);
        System.out.println(JSONObject.toJSONString(fDetectResult));

        // 人脸对比调用
        FaceVerifyResult fVerifyResult = faceCVUtils.faceVerify(netImg,netImg1);
        System.out.println(JSONObject.toJSONString(fVerifyResult));
        return JSONObject.toJSONString(getUser());
    }


    @PostMapping(value = "/face/verify/upload")
    public R verify(@RequestParam("file") MultipartFile multipartFile, FaceVerifyVO faceVerifyVO) throws Exception {



        SysUserEntity userEntity = getUser();
        faceVerifyVO.setUsername(userEntity.getUsername());



        // 人脸对比调用
//        String img = "";
//        String img1 = "";
        return cvFaceVerifyService.verifyFaceSave(multipartFile,faceVerifyVO,userEntity);
    }


}
