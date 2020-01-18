package com.sym.modules.cv.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sym.modules.cv.entity.CvFaceVerifyEntity;
import com.sym.modules.cv.service.CvFaceVerifyService;
import com.sym.common.utils.PageUtils;
import com.sym.common.utils.R;



/**
 * 人脸识别对比记录表
 *
 * @author suyongming
 * @email 476231418@qq.com
 * @date 2019-08-30 16:18:58
 */
@RestController
@RequestMapping("cv/cvfaceverify")
public class CvFaceVerifyController {
    @Autowired
    private CvFaceVerifyService cvFaceVerifyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("cv:cvfaceverify:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cvFaceVerifyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("cv:cvfaceverify:info")
    public R info(@PathVariable("id") Long id){
		CvFaceVerifyEntity cvFaceVerify = cvFaceVerifyService.getById(id);

        return R.ok().put("cvFaceVerify", cvFaceVerify);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("cv:cvfaceverify:save")
    public R save(@RequestBody CvFaceVerifyEntity cvFaceVerify){
		cvFaceVerifyService.save(cvFaceVerify);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("cv:cvfaceverify:update")
    public R update(@RequestBody CvFaceVerifyEntity cvFaceVerify){
		cvFaceVerifyService.updateById(cvFaceVerify);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("cv:cvfaceverify:delete")
    public R delete(@RequestBody Long[] ids){
		cvFaceVerifyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
