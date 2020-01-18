package com.sym.common.utils;

import lombok.Data;

/**
 * @author suyongming
 * @date 创建时间：2019/8/30 13:59
 */
@Data
public class FaceDetectResult {
    private int errno;// 0为成功，非0为失败，详细说明参见错误码
    private String err_msg;// 处理失败时的说明
    private String request_id;// request_id 请求id信息
    private int face_num;// 检测出来的人脸个数
    private int[] face_rect;// 返回人脸矩形框，分别是[left, top, width, height], 如有多个人脸，则依次顺延，返回矩形框。如有两个人脸则返回[left1, top1, width1, height1, left2, top2, width2, height2]
    private float[] face_prob;// 返回人脸概率, 0-1之间，如有多个人脸，则依次顺延。如有两个人脸则返回[face_prob1, face_prob2]
    private float[] pose;// 返回人脸姿态[yaw, pitch, roll]， yaw为左右角度，取值[-90, 90]，pitch为上下角度，取值[-90, 90]， roll为平面旋转角度，取值[-180, 180]，如有多个人脸，则依次顺延
    private int landmark_num;// 特征点数目，目前固定为105点(顺序：眉毛24点，眼睛32点，鼻子6点，嘴巴34点，外轮廓9点)
    private float[] landmark;// 特征点定位结果，每个人脸返回一组特征点位置，表示方式为（x0, y0, x1, y1, ……）；如有多个人脸，则依次顺延，返回定位浮点数
    private float[] iris;// 左右两个瞳孔的中心点坐标和半径，每个人脸6个浮点数，顺序是[left_iris_cenpt.x, left_iris_cenpt.y, left_iris_radius, right_iris_cenpt.x, right_iris_cenpt.y, right_iris_radis]
}
