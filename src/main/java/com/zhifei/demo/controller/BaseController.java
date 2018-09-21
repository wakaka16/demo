package com.zhifei.demo.controller;

import com.zhifei.demo.common.bean.Const;
import com.zhifei.demo.common.bean.ResponseBean;
import com.zhifei.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {
     //获取响应对象
    protected ResponseBean getResponseBean(){
        return new ResponseBean();
    }

    // 获取请求的request
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    // 获取session
    protected HttpSession getSession(){
        return getRequest().getSession();
    }

    //获取当前登录用户
  protected User getLoginUser(){
      return (User)getSession().getAttribute(Const.LOGIN_SESSION_KEY);
    }

}
