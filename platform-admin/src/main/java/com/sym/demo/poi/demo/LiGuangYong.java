package com.sym.demo.poi.demo;


import com.alibaba.fastjson.JSONObject;
import com.sym.common.utils.poi.EasyPoiUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author suyongming
 * @date ：2020/6/17 21:00
 */
public class LiGuangYong {

    private static final String LIGUANGYONG = "C:\\temp\\宁波索引文件.xlsx";

    public static void main(String[] args) throws IOException {
        List<LiGuangYongDTO> audios = EasyPoiUtils.importExcel(LIGUANGYONG, 1, 1, LiGuangYongDTO.class);
//        String strUrl = "http://mao.metlife.cn:81/jplayer/index.html?filepath=\\\\10.165.1.155\\New Recordings2\\vox\\大都会\\大都会_20191107\\20191107\\\\\\e0306220191107152806.wav&&type=wav&&site=BOCOM交通银行";
//        strUrl = ecode(strUrl);
//        String result = HttpClientUtil.get(strUrl);

        System.out.println(audios.size());
        Map<String,List<LiGuangYongDTO>> audiosGroupByPolicyNo = audios.stream().collect(
                Collectors.groupingBy(LiGuangYongDTO::getPolicyNo)
        );

        String jsonString = JSONObject.toJSONString(audiosGroupByPolicyNo);
//        audios.forEach(System.out::println);



    }

    private static String ecode (String url){
        //被转码后的url
        String result = "";
        //需要转码的url

        int index = url.indexOf("?");
        result = url.substring(0,index+1);
        String temp = url.substring(index+1);
        try {
            //URLEncode转码会将& ： / = 等一些特殊字符转码,(但是这个字符  只有在作为参数值  时需要转码;例如url中的&具有参数连接的作用，此时就不能被转码)
            String encode = URLEncoder.encode(temp, "utf-8");
            System.out.println(encode);
            encode = encode.replace("%3D",  "=");
            encode = encode.replace("%2F", "/");
            encode = encode.replace("+", "%20");
            encode = encode.replace("%26", "&");
            result += encode;
            System.out.println("转码后的url:"+result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
