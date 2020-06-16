package com.sym.modules.poi.test;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.sym.modules.poi.dto.GraphicsPushHistoryExcelPoiDTO;
import com.sym.modules.poi.util.EasyPoiUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yongming.su
 * @version 1.0
 * @date 2020/6/16 14:38
 */
public class EasyPoiDemo {
    private static final String READ_PATH = "C:\\Users\\suyon\\file\\20200616bs.xlsx";

    public static void main(String[] args) throws Exception {
        readLocalExcel();
    }

    /**
     * 读取本地Excel
     */
    private static void readLocalExcel() throws Exception {
        // EasyExcel 写法
        //必须要有HeaderRows？
        List<GraphicsPushHistoryExcelPoiDTO> graphicHistories = EasyPoiUtils.importExcel(READ_PATH, 1,1,GraphicsPushHistoryExcelPoiDTO.class);

        System.out.println("过滤前count:"+graphicHistories.size());
        // 去重   确保组合唯一条件: 外部系统Id,图文Id,推送Id
        List<GraphicsPushHistoryExcelPoiDTO> filteredList = graphicHistories.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(
                        s -> s.getDestinationSystemType() + s.getMediaId() + s.getMsgDataId()))), ArrayList::new));

        System.out.println("过滤后count:"+filteredList.size());
        System.out.println(filteredList.size());

        filteredList.forEach(System.out::println);




    }
}
