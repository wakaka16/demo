package com.zhifei.demo.controller.front;

import com.zhifei.demo.common.aop.LoggerManage;
import com.zhifei.demo.common.bean.BusinessException;
import com.zhifei.demo.common.bean.Const;
import com.zhifei.demo.common.bean.ResponseBean;
import com.zhifei.demo.controller.BaseController;
import com.zhifei.demo.entity.User;
import com.zhifei.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
* @Description:    前台用户控制器
* @Author:         wangXiaoLong
* @CreateDate:     2018/8/2 10:14
* @UpdateUser:     wangXiaoLong
* @UpdateDate:     2018/8/2 10:14
* @UpdateRemark:   修改内容
*/
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String index(){
        return "frontPages/login-register";
    }

    @RequestMapping(value = "/user/login.do",method = RequestMethod.POST)
    @ResponseBody
    @LoggerManage(description="用户登录")
    public ResponseBean login(@Validated User user,HttpServletResponse response) throws BusinessException {
        User loginUser =userService.login(user);
        if(null!=loginUser){
            // 登录用户获取session，保存属性
            getSession().setAttribute(Const.LOGIN_SESSION_KEY,loginUser);
            // 将登录用户保存到cookie
            Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY,loginUser.getUserId().toString() );//cookieSign(loginUser.getId().toString())
            cookie.setMaxAge(30*24*60*60);//30天Const.COOKIE_TIMEOUT
            cookie.setPath("/");
            response.addCookie(cookie);
            return getResponseBean().setSuccess("登录成功");
        }else{
            return getResponseBean().setFail("111");
        }

    }

    @RequestMapping("/list")
    @LoggerManage(description = "查询所有用户")
    public String list(Model model){
        List<User> userList = new ArrayList<>();
        for(int i=0;i<10;i++){
            User user = new User(1000L,"user"+i,"pwd"+i,"logo"+i);
            userList.add(user);
        }
        model.addAttribute("userList",userList);
        return "front/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        //http://localhost:8001/delete?id=1000
        System.out.println(id);
        return "redirect:/admin/user/list";
    }
}
