package com.chenyu.util;

/**
 * @author xinYing
 */
public class WebUtil {
    /**
     * 是否为空或null
     * @param param     参数
     * @return
     */
    public static Boolean isNull(String param){
        return !(param!=null && !"".equals(param));
    }

    /**
     * 邮箱验证
     * @param param
     * @return
     */
    public static Boolean emailLike(String param){
        return  param.matches("\\w+@\\w+(\\.\\w+)+");
    }

}
