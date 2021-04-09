package com.lisy.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lisy
 * @Date: 2021/4/1
 * @version: 1.0
 * @Description: "公共返回"
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private Integer code;
    private String message;
    private Object data;

    /**
     * 成功返回
     * @param message
     * @return
     */
    public static RespBean success(String message){
        return new RespBean(200, message, null);
    }

    /**
     * 成功返回
     * @param message
     * @param data
     * @return
     */
    public static RespBean success(String message, Object data){
        return new RespBean(200, message, data);
    }

    /**
     * 失败返回
     * @param message
     * @return
     */
    public static RespBean error(String message){
        return new RespBean(500, message, null);
    }

    /**
     * 失败返回
     * @param message
     * @param data
     * @return
     */
    public static RespBean error(String message, Object data){
        return new RespBean(500, message, data);
    }
}
