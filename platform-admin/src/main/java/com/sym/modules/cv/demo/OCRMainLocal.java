package com.sym.modules.cv.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.green.model.v20180509.ImageSyncScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.sym.common.utils.aliyun.ClientUploader;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * @author suyon
 * @date 2019-12-05 10:42
 */
public class OCRMainLocal {

    public static void main(String[] args) throws Exception {
        IClientProfile profile = DefaultProfile
                .getProfile("cn-shanghai", "LTAIPXQux476231418FJ7477m", "cUW4bir9z0pB476231418JX05g3ozMytBiVSgWt");

        DefaultProfile
                .addEndpoint("cn-shanghai", "cn-shanghai", "Green", "green.cn-shanghai.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);

        ImageSyncScanRequest imageSyncScanRequest = new ImageSyncScanRequest();
        // 指定api返回格式
        imageSyncScanRequest.setAcceptFormat(FormatType.JSON);
        // 指定请求方法
        imageSyncScanRequest.setMethod(MethodType.POST);
        imageSyncScanRequest.setEncoding("utf-8");
        //支持http和https
        imageSyncScanRequest.setProtocol(ProtocolType.HTTP);

        JSONObject httpBody = new JSONObject();
        /**
         * 设置要检测的场景
         * ocr: ocr或者ocr卡证识别
         */
        httpBody.put("scenes", Arrays.asList("ocr"));

        /**
         * 设置待检测图片， 一张图片一个task，最多支持100张图片同时检测，即需要构建100个task
         * 多张图片同时检测时，处理的时间由最后一个处理完的图片决定。因此通常情况下批量检测的平均rt比单张检测的要长, 一次批量提交的图片数越多，rt被拉长的概率越高
         * 这里以单张图片检测作为示例, 如果是批量图片检测，请自行构建多个task
         * 本地图片相对于互联网图片链接来说，多了一个上传步骤，上传后取返回的链接进行检测
         */
        ClientUploader clientUploader = ClientUploader.getImageClientUploader(profile, false);
        String url = null;
        try{
            url = clientUploader.uploadFile("D:\\image\\cv\\Z.jpg");
        }catch (Exception e){
            System.out.println("upload file to server fail.");
        }
        JSONObject task = new JSONObject();
        task.put("dataId", UUID.randomUUID().toString());
        task.put("url", url);
        task.put("time", new Date());
        httpBody.put("tasks", Arrays.asList(task));

        //ocr卡证识别，设置识别卡证类型
        JSONObject cardExtras = new JSONObject();
        //身份证正面识别
        cardExtras.put("card", "id-card-front");
        //身份证反面
        //cardExtras.put("card", "id-card-back");
        httpBody.put("extras", cardExtras);

        imageSyncScanRequest.setHttpContent(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(httpBody.toJSONString()), "UTF-8", FormatType.JSON);
        /**
         * 请设置超时时间, 服务端全链路处理超时时间为10秒，请做相应设置
         * 如果您设置的ReadTimeout小于服务端处理的时间，程序中会获得一个read timeout异常
         */
        imageSyncScanRequest.setConnectTimeout(3000);
        imageSyncScanRequest.setReadTimeout(10000);
        HttpResponse httpResponse = null;
        try {
            httpResponse = client.doAction(imageSyncScanRequest);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        //服务端接收到请求，并完成处理返回的结果
        if(httpResponse != null && httpResponse.isSuccess()){
            JSONObject scrResponse = JSON.parseObject(org.apache.commons.codec.binary.StringUtils.newStringUtf8(httpResponse.getHttpContent()));
            System.out.println(JSON.toJSONString(scrResponse));
            int requestCode = scrResponse.getIntValue("code");
            //每一张图片的检测结果
            JSONArray taskResults = scrResponse.getJSONArray("data");
            if (200 == requestCode) {
                for (Object taskResult : taskResults) {
                    //单张图片的处理结果
                    int taskCode = ((JSONObject)taskResult).getIntValue("code");
                    //图片要检测的场景的处理结果, 如果是多个场景，则会有每个场景的结果
                    JSONArray sceneResults = ((JSONObject)taskResult).getJSONArray("results");
                    if(200 == taskCode){
                        for (Object sceneResult : sceneResults) {
                            String scene = ((JSONObject)sceneResult).getString("scene");
                            String suggestion = ((JSONObject)sceneResult).getString("suggestion");
                            //do something
                            //有识别出卡证信息
                            if("review" .equals(suggestion) && "ocr".equals(scene)){
                                JSONObject idCardInfo =  ((JSONObject) sceneResult).getJSONObject("idCardInfo");
                                System.out.println(idCardInfo.toJSONString());
                            }
                        }
                    }else{
                        //单张图片处理失败, 原因视具体的情况详细分析
                        System.out.println("task process fail. task response:" + JSON.toJSONString(taskResult));
                    }
                }
            } else {
                /**
                 * 表明请求整体处理失败，原因视具体的情况详细分析
                 */
                System.out.println("the whole image scan request failed. response:" + JSON.toJSONString(scrResponse));
            }
        }
    }
}
