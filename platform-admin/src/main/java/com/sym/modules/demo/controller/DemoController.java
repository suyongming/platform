package com.sym.modules.demo.controller;

import com.sym.common.utils.PageUtils;
import com.sym.common.utils.R;
import com.sym.demo.event.MemberLikeEvent;
import io.swagger.annotations.Api;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description
 * @Author: sym
 * @Date: 2025/1/9 11:30
 */
@RestController
@RequestMapping("/demo")
@Api("事件Demo")
public class DemoController {
    @Resource
    private ApplicationEventPublisher publisher;

    @GetMapping("event")
    public R list(@RequestParam Map<String, Object> params){
        publisher.publishEvent(new MemberLikeEvent(this, 1L, 2L));
        return R.ok();
    }

}
