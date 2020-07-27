package com.lt.util;

import com.alibaba.fastjson.JSONObject;
import com.lt.winHaoChenCad.SocketProperties;

/**
 * @author liangtao
 * @Date 2020/7/21
 **/
public class JsonUtil {
    public static String makeJson(Object cadProperties){
        String cadJson = JSONObject.toJSONString(cadProperties);
        SocketProperties socketProperties = new SocketProperties();
        socketProperties.setInput(cadJson);
        socketProperties.setStatus(0);
        return JSONObject.toJSONString(socketProperties);
    }
}