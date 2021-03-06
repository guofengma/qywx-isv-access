package com.rishiqing.qywx.service.model.isv;

import java.util.Date;

public class SuiteVO {
    private Long id;

    private String suiteName;  // 套件名字
    private String suiteKey;  // suite id
    private String suiteSecret;  // suite的唯一secret，与key对应
    private String encodingAesKey;  // 回调信息加解密参数
    private String token;  //  已填写用于生成签名和校验毁掉请求的合法性
    private String corpId;  // isv所在公司的corpId

    private String rsqAppName;  // 日事清调用的名称
    private String rsqAppToken;  // 日事清调用的token

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getSuiteKey() {
        return suiteKey;
    }

    public void setSuiteKey(String suiteKey) {
        this.suiteKey = suiteKey;
    }

    public String getSuiteSecret() {
        return suiteSecret;
    }

    public void setSuiteSecret(String suiteSecret) {
        this.suiteSecret = suiteSecret;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getRsqAppName() {
        return rsqAppName;
    }

    public void setRsqAppName(String rsqAppName) {
        this.rsqAppName = rsqAppName;
    }

    public String getRsqAppToken() {
        return rsqAppToken;
    }

    public void setRsqAppToken(String rsqAppToken) {
        this.rsqAppToken = rsqAppToken;
    }

    @Override
    public String toString() {
        return "SuiteVO{" +
                "id=" + id +
                ", suiteName='" + suiteName + '\'' +
                ", suiteKey='" + suiteKey + '\'' +
                ", suiteSecret='" + suiteSecret + '\'' +
                ", encodingAesKey='" + encodingAesKey + '\'' +
                ", token='" + token + '\'' +
                ", corpId='" + corpId + '\'' +
                ", rsqAppName='" + rsqAppName + '\'' +
                ", rsqAppToken='" + rsqAppToken + '\'' +
                '}';
    }
}
