package com.sym.modules.poi.test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.sym.modules.poi.dto.GraphicsPushHistoryEasyExcelDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author yongming.su
 * @version 1.0
 * @date 2020/6/16 14:38
 */
public class EasyExcelDemo {
    private static final String READ_PATH = "C:\\Users\\suyon\\file\\20200616bs.xlsx";
    public static void main(String[] args) throws FileNotFoundException {

        readLocalExcel();
    }

    /**
     * 读取本地Excel
     * */
    private static void readLocalExcel() throws FileNotFoundException {
        // EasyExcel 写法
        Sheet sheet = new Sheet(1, 2, GraphicsPushHistoryEasyExcelDTO.class);
        List list = EasyExcelFactory.read(new FileInputStream(READ_PATH) , sheet);
        list.forEach(System.out::println);

    }
}
