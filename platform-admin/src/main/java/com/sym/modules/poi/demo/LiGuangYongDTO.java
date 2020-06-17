package com.sym.modules.poi.demo;


import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author suyongming
 * @date ：2020/6/17 21:05
 */
public class LiGuangYongDTO {

    @Excel(name = "保单号" ,orderNum = "0")
    private String policyNo;

    @Excel(name = "f_policynum" ,orderNum = "1")
    private String policynum;

    @Excel(name = "F_Mobile" ,orderNum = "2")
    private String mobile;

    @Excel(name = "F_Task" ,orderNum = "3")
    private String task;

    @Excel(name = "F_InsertTime" ,orderNum = "4")
    private String insertTime;

    @Excel(name = "投保人电话" ,orderNum = "5")
    private String tbMobile;

    @Excel(name = "承保日期" ,orderNum = "6")
    private String cbTime;

    @Excel(name = "项目名称" ,orderNum = "7")
    private String projectName;

    @Excel(name = "播放地址" ,orderNum = "8")
    private String httpUrl;

    @Excel(name = "是否有录音索引" ,orderNum = "9")
    private String isIndex;


    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicynum() {
        return policynum;
    }

    public void setPolicynum(String policynum) {
        this.policynum = policynum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getTbMobile() {
        return tbMobile;
    }

    public void setTbMobile(String tbMobile) {
        this.tbMobile = tbMobile;
    }

    public String getCbTime() {
        return cbTime;
    }

    public void setCbTime(String cbTime) {
        this.cbTime = cbTime;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(String isIndex) {
        this.isIndex = isIndex;
    }

    @Override
    public String toString() {
        return "LiGuangYongDTO{" +
                "policyNo='" + policyNo + '\'' +
                ", policynum='" + policynum + '\'' +
                ", mobile='" + mobile + '\'' +
                ", task='" + task + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", tbMobile='" + tbMobile + '\'' +
                ", cbTime='" + cbTime + '\'' +
                ", projectName='" + projectName + '\'' +
                ", httpUrl='" + httpUrl + '\'' +
                ", isIndex='" + isIndex + '\'' +
                '}';
    }
}
