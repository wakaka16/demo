package com.zhifei.demo.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 前端相应结果
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ResponseBean<T> {
    // 响应代码
    private String code="000000";
    //响应信息
    private String message="操作成功";
    //响应数据
    private T data;

    /**
     * 失败设置提示信息
     * @param message
     */
    public ResponseBean setFail(String message){
        this.message=message;
        this.code="000001";
        return this;
    }

    /**
     * 成功设置提示信息
     * @param message
     */
    public ResponseBean setSuccess(String message){
        this.message=message;
        return this;
    }

    /**
     * 成功设置数据
     * @param data
     */
    public ResponseBean setData(T data){
        this.data=data;
        return this;
    }
}
