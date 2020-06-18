package com.sym.demo.poi.controller;

import com.sym.demo.poi.dto.GraphicsPushHistoryExcelPoiDTO;
import com.sym.demo.poi.test.EasyPoiDemo;
import com.sym.common.utils.poi.EasyPoiUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yongming.su
 * @version 1.0
 * @date 2020/6/16 17:25
 */
@RestController
@RequestMapping("poi")
public class EasyPoiController {
    /**
     * 1.普通easyPOI导出
     */
    @GetMapping("/demo/export")
    @ApiOperation("月结列表导出Excel")
    public void export(HttpServletResponse response) throws Exception {
        // 假设数据来源于是从数据库
        List<GraphicsPushHistoryExcelPoiDTO> list = EasyPoiDemo.readLocalExcel();

        EasyPoiUtils.exportExcel(list, "图文推送历史数据传输实体信息.", "sheet1", GraphicsPushHistoryExcelPoiDTO.class, "数据导出Excel.xlsx", response);

    }

}
