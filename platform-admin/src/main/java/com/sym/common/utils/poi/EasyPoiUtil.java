package com.sym.common.utils.poi;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.sym.common.exception.CustomException;
import com.sym.common.utils.BeanCopierUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: sym
 * @Date: 2021/9/18
 */
public class EasyPoiUtil {

    /**
     * @param list         需要导出的List
     * @param pojoClass    泛型
     * @param exportParams Excel 导出参数
     * @Description:
     * @Param: [list, pojoClass, fileName, exportParams]
     * @return: java.io.InputStream
     * @Author: sym
     * @Date: 2021/9/18
     */
    public static InputStream inputStreamExport(List<?> list, Class<?> pojoClass, ExportParams exportParams) {
        List<?> objects = BeanCopierUtil.copyList(list, pojoClass);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, objects);
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byte[] byteArray = bos.toByteArray();
            InputStream is = new ByteArrayInputStream(byteArray);
            return is;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 仅用于销量数据导出
     *
     * @param list      导出的对相结合
     * @param title     excel中第一行的标题
     * @param sheetName sheetName
     * @param pojoClass 导出的模板类型 @Excel
     * @param fileName  文件名
     * @param response  HttpServletResponse
     * @Description:
     * @return: void
     * @Author: sym
     * @Date: 2021/9/23
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName,
                                      HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            //throw new NormalException(e.getMessage());
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    /**
     * @Description: 本地的Excel 解析为 java对象集合
     * @Param: [filePath, titleRows, headerRows, pojoClass]
     * @return: java.util.List<T>
     * @Author: sym
     * @Date: 2021/9/23
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            //throw new NormalException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            //throw new NormalException(e.getMessage());
        }
        return list;
    }

    /**
     * @Description: web的 MultipartFile excel 解析为 java对象集合
     * @Param: [file, titleRows, headerRows, pojoClass]
     * @param file 上传的excel文件
     * @param titleRows excel 第一个合并的单元格title  如果没有传0就行, 如果有标题就传1
     * @param headerRows 相当于列名、字段名  目前传1就好
     * @return: java.util.List<T>
     * @Author: sym
     * @Date: 2021/9/23
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        InputStream inputStream = null;

        try {
            inputStream = file.getInputStream();
            list = ExcelImportUtil.importExcel(inputStream, pojoClass, params);
            file.getInputStream().close();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("excel文件不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("异常文件解析");
        } finally {
            inputStream.close();
        }
        return list;
    }

}
