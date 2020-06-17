package com.sym.modules.poi.test;

import com.sym.modules.poi.dto.GraphicsPushHistoryExcelPoiDTO;
import com.sym.modules.poi.util.EasyPoiUtils;
import com.sym.modules.poi.util.HttpClientUtil;

import java.util.List;

/**
 * @author suyongming
 * @date ：2020/6/17 21:00
 */
public class LiGuangYong {

    private static final String LIGUANGYONG = "C:\\Users\\suyon\\file\\20200617.xlsx";

    public static void main(String[] args) {


        List<GraphicsPushHistoryExcelPoiDTO> graphicHistories = EasyPoiUtils.importExcel(LIGUANGYONG, 1, 1, GraphicsPushHistoryExcelPoiDTO.class);


        String url = "https://www.baidu.com";
        String result = HttpClientUtil.doGet(url);
        System.out.println(result);

    }
}
