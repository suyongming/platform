package com.sym.common.utils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author suyongming
 * @date 创建时间：2019/8/30 14:23
 */
@Slf4j
public class Img2Base64 {
    /**
     * 将网络图片编码为base64
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String encodeImageToBase64(URL url) throws Exception {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        log.info("图片的路径为:" + url.toString());
        //打开链接
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //关闭输入流
            inStream.close();
            byte[] data = outStream.toByteArray();
            //对字节数组Base64编码
            Base64 encoder = new Base64();
            String base64 = encoder.encodeToString(data);
//            log.info("网络文件[{}]编码成base64字符串:[{}]" + url.toString() + base64);
            return base64;//返回Base64编码过的字节数组字符串
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error:Img2Base64");
        }
    }

    //1-压缩图片
    /**
     * @Param input jpg图片的inputStream
     * @Param width 预备压缩的图片宽度 int
     * @Param quality 质量设置必须在0.0f和1.0f范围内（包括0.0f和1.0f）
     * 默认 100宽度 0.2质量
     * @Param min 最低阈值 默认为 50KB
     *
     * */
    public static InputStream  compressFile(InputStream input,Integer width,float quality,Integer min) throws IOException {
        if(width == null || width == 0){
            width = 100;
        }

        if(Float.compare(quality,0.0f) == 0){
            quality = 0.2f;
        }

        if(min == 0 || min == null){
            //默认为 50KB
            min = 50000;
        }
        //1-压缩图片
        BufferedImage bufImg = ImageIO.read(input);// 把图片读入到内存中
        bufImg = Thumbnails.of(bufImg).width(width).keepAspectRatio(true).outputQuality(quality).asBufferedImage();//压缩:宽度100px,长度自适应;质量压缩到0.1
        ByteArrayOutputStream bos = new ByteArrayOutputStream();// 存储图片文件byte数组
        ImageIO.write(bufImg, "jpg", bos); // 图片写入到 ImageOutputStream
        input = new ByteArrayInputStream(bos.toByteArray());
        int available = input.available();
        //2-如果大小超过50KB，继续压缩

        if(available > min){
            compressFile(input,width,quality,min);
        }
        return input;

    }

    //2-InputStream转化为base64
    public static String getBase64FromInputStream(InputStream in) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String str = new String(Base64.encodeBase64(data));
        System.out.println( "str length: " + str.length() + "  str: " + str);
        return str;
    }

    //3-Base64解码并生成图片
    public static boolean GenerateImage(String base64str,String savepath) { //对字节数组字符串进行Base64解码并生成图片
        if (base64str == null) //图像数据为空
            return false;
        Base64 decoder = new Base64();
        try {
            //Base64解码
            byte[] b = decoder.decode(base64str);
            // System.out.println("解码完成");
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据（这一步很重要）
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(savepath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
