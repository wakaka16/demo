package com.zhifei.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
* @Description:    管理员用户（前台登录用户）
* @Author:         wangXiaoLong
* @CreateDate:     2018/7/31 17:56
* @UpdateUser:     wangXiaoLong
* @UpdateDate:     2018/7/31 17:56
* @UpdateRemark:   修改内容
*/
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class User {
    private Long userId;
    @NotNull(message="User.name.non")
    private String name;
    @NotNull(message="User.pwd.non")
    private String pwd;
    private String logo;
}
