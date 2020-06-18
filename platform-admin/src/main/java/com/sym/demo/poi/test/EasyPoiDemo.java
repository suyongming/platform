package com.sym.demo.poi.test;

import com.alibaba.fastjson.JSONObject;
import com.sym.demo.poi.dto.GraphicResultPushEasyPoiDTO;
import com.sym.demo.poi.dto.GraphicsPushHistoryExcelPoiDTO;
import com.sym.common.utils.poi.EasyPoiUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yongming.su
 * @version 1.0
 * @date 2020/6/16 14:38
 */
public class EasyPoiDemo {
    private static final String READ_PATH = "C:\\Users\\suyon\\file\\20200616bs.xlsx";
    private static final String KAFKA_PUSH_READ_PATH = "C:\\Users\\suyon\\file\\20200617.xlsx";

    public static void main(String[] args) throws Exception {
//        readLocalExcel();
        readLocalExcel2();
    }

    /**
     * 读取本地Excel Demo
     * 主要用于本地练习
     */
    public static List<GraphicsPushHistoryExcelPoiDTO> readLocalExcel() throws Exception {
        // EasyExcel 写法
        //必须要有HeaderRows?
        List<GraphicsPushHistoryExcelPoiDTO> graphicHistories = EasyPoiUtils.importExcel(READ_PATH, 1, 1, GraphicsPushHistoryExcelPoiDTO.class);

        System.out.println("过滤前count:" + graphicHistories.size());
        // 根据 外部系统Id,图文Id,推送Id 组合唯一条件去重,去重时 保留老的一条数据
        List<GraphicsPushHistoryExcelPoiDTO> filteredList = graphicHistories.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<>(
                                        Comparator.comparing(
                                                s -> s.getDestinationSystemType() + s.getMsgDataId()
                                        )
                                )
                        ),
                        ArrayList::new
                )
        );

        System.out.println("过滤后count:" + filteredList.size());
        System.out.println(filteredList.size());

        return filteredList;
    }

    public static void readLocalExcel2() {
        List<GraphicResultPushEasyPoiDTO> pushRecords = EasyPoiUtils.importExcel(KAFKA_PUSH_READ_PATH, 1, 1, GraphicResultPushEasyPoiDTO.class);
        Map<String, List<GraphicResultPushEasyPoiDTO>> recordsGroupByMediaId = pushRecords.stream()
                .limit(300)
                .collect(
                        Collectors.groupingBy(
                                GraphicResultPushEasyPoiDTO::getMediaId
                        )
                );


        System.out.println(JSONObject.toJSONString(recordsGroupByMediaId));
        // recordsGroupByBatch.forEach((k,v)->{
        //     System.out.println("k:"+k+":\nv:"+v);
        // });

        // doGetSendInfo(recordsGroupByBatch);

    }
}
