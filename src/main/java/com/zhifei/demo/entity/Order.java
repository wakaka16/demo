package com.zhifei.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
* @Description:    订单
* @Author:         wangXiaoLong
* @CreateDate:     2018/7/31 18:00
* @UpdateUser:     wangXiaoLong
* @UpdateDate:     2018/7/31 18:00
* @UpdateRemark:   修改内容
*/
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;//明细id
    private Long accountIdA;//交易甲方id
    private Long accountIdB;//交易乙方id
    private Integer amount;//交易数量
    private String date;//交易日期



}
