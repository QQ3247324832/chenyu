package com.chenyu.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpServletRequestWrapper 的装饰类
 * @author xinYing
 */
public class CodeUtil extends HttpServletRequestWrapper {
    private String encode = null;
    public CodeUtil(HttpServletRequest request,String encode) {
        super(request);
        this.encode=encode;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        Map<String, String[]> newParameterMap = new HashMap<>();
        try {
        for (Map.Entry<String,String[]> entry : parameterMap.entrySet()){
            String key = entry.getKey();
            String[] value = entry.getValue();
            String[] arr = new String[value.length];
            for (int i = 0 ; i <value.length ; i++) {
                String s = new String(value[i].getBytes("iso8859-1"), encode);
                arr[i]=s;
            }
            newParameterMap.put(key, arr);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newParameterMap;
    }

    @Override
    public String getParameter(String name) {

        String[] values = getParameterValues(name);
        //将数组中的第一元素作为方法返回值使用。
        return values==null?null:values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return getParameterMap().get(name);
    }
}
