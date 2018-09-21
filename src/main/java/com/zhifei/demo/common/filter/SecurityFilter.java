package com.zhifei.demo.common.filter;

import com.zhifei.demo.common.bean.Const;
import com.zhifei.demo.entity.User;
import com.zhifei.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecurityFilter implements Filter {

    protected Logger logger = Logger.getLogger(this.getClass());
    // 不过滤的路径（绿色路径仓库）
    private static List<String> GreenUrlList = new ArrayList<>();
    @Autowired
    private UserService userService;


    @Override
    public void init(FilterConfig filterConfig) {
        GreenUrlList.add("/login");
        GreenUrlList.add("/register");
        GreenUrlList.add("/index");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        // 请求路径
        String uri = request.getRequestURI();
        // 未登录
        if (request.getSession().getAttribute(Const.LOGIN_SESSION_KEY) == null) {

            // 静态文件不过滤
            if (containsSuffix(uri)) {
                filterChain.doFilter(request, response);
                return;
            }

            // 绿色路由不过滤
            for (int i = 0; i < GreenUrlList.size(); i++) {
                if (uri.indexOf(GreenUrlList.get(i)) != -1) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            // session为null,获取用户cookies
            Cookie[] cookies = request.getCookies();
            if (null == cookies) {// cookie为null
                response.sendRedirect("/login");//登录界面
                filterChain.doFilter(request, response);
                return;
            }

            // cookie登录
            boolean flag = false;
            Long userId = null;
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookies[i].getName().equals(Const.LOGIN_SESSION_KEY)) {
                    if (StringUtils.isNotEmpty(cookie.getValue())) {
                        userId = Long.parseLong(cookie.getValue());
                        flag = true;
                        break;
                    }
                }
            }

            if (flag) {//已登录
                User cookieUser = userService.cookieLogin(userId);
            } else {//未登录
                response.sendRedirect("/login");//登录界面
                filterChain.doFilter(request, response);
                return;
            }

        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    /**
     * 含有以下后缀觉得url
     *
     * @param url
     * @return
     */
    private boolean containsSuffix(String url) {
        if (url.endsWith(".js")
                || url.endsWith(".css")
                || url.endsWith(".jpg")
                || url.endsWith(".gif")
                || url.endsWith(".png")
                || url.endsWith(".eot")
                || url.endsWith(".svg")
                || url.endsWith(".ttf")
                || url.endsWith(".woff")
                || url.endsWith(".ico")
                || url.endsWith(".woff2")) {
            return true;
        } else {
            return false;
        }
    }
}
