package com.rishiqing.qywx.service.util.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.rishiqing.qywx.service.constant.RequestUrl;
import com.rishiqing.qywx.service.exception.AccessTokenExpiredException;
import com.rishiqing.qywx.service.exception.HttpException;
import com.rishiqing.qywx.service.model.corp.CorpDeptVO;
import com.rishiqing.qywx.service.model.corp.CorpTokenVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class HttpUtilCorp {
    private RequestClient requestClient;

    public HttpUtilCorp(RequestClient requestClient) {
        this.requestClient = requestClient;
    }

    public JSONObject getDepartmentList(CorpTokenVO corpTokenVO, CorpDeptVO corpDeptVO) throws UnirestException, HttpException {
        Map<String, String> options = new HashMap<>();
        options.put("corpId", corpTokenVO.getCorpId());
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("access_token", corpTokenVO.getCorpToken());
        if(corpDeptVO != null){
            queryMap.put("id", corpDeptVO.getDeptId());
        }
        return requestClient.get(
                RequestUrl.DEPARTMENT_LIST,
                queryMap,
                options
        );
    }

    public JSONObject getDepartmentStaffList(CorpTokenVO corpTokenVO, @Nullable CorpDeptVO corpDeptVO) throws HttpException, UnirestException {
        Map<String, String> options = new HashMap<>();
        options.put("corpId", corpTokenVO.getCorpId());
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("access_token", corpTokenVO.getCorpToken());
        queryMap.put("fetch_child", "1");  //递归获取
        Long deptId = 1L;
        if(corpDeptVO != null){
            deptId = corpDeptVO.getDeptId();
        }
        queryMap.put("department_id", deptId);

        return requestClient.get(
                RequestUrl.DEPARTMENT_STAFF_LIST,
                queryMap,
                options
        );
    }

    public RequestClient getRequestClient() {
        return requestClient;
    }

    public void setRequestClient(RequestClient requestClient) {
        this.requestClient = requestClient;
    }
}