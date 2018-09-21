package com.zhifei.demo.controller.backend;

import com.zhifei.demo.common.aop.LoggerManage;
import com.zhifei.demo.common.bean.Const;
import com.zhifei.demo.common.bean.ResponseBean;
import com.zhifei.demo.controller.BaseController;
import com.zhifei.demo.entity.User;
import com.zhifei.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
* @Description:    后台用户控制器
* @Author:         wangXiaoLong
* @CreateDate:     2018/8/2 10:14
* @UpdateUser:     wangXiaoLong
* @UpdateDate:     2018/8/2 10:14
* @UpdateRemark:   修改内容
*/
@Controller
@RequestMapping(value = "/backend/admin")
public class AdminController extends BaseController {



}
