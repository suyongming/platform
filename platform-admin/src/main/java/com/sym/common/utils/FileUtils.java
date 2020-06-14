package com.sym.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author suyongming
 * @date ：2020/4/19 22:43
 */
@Slf4j
public class FileUtils {
    public static final String FILE_PATH = "D:\\image\\upload\\" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "\\";//文件指定存放的路径

    /**
     * @Param filename
     * @Param file
     *SimpleDateFormat
     * @return filePath + filename
     * */
    public static String executeUpload(String filename ,MultipartFile file) throws Exception {
        //服务器端保存的文件对象
        File serverFile = new File(FILE_PATH + filename);

        if (!serverFile.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            serverFile.getParentFile().mkdir();
            try {
                //创建文件
                serverFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);

        return FILE_PATH + filename;

    }
}
