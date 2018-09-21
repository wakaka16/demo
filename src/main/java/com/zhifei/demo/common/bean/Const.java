package com.zhifei.demo.common.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Const {

    public static String LOGIN_SESSION_KEY = "demo_user";//登录我这个系统的用户统称为服务+user：例ali_user

    public static String BASE_PATH;//基础路径
    @Autowired(required = true)//注入basePath
    public void setBasePath(@Value("${demo.base.path}")String basePath){
        Const.BASE_PATH=basePath;
    }

    public static String DEFAULT_LOGO="default_logo.jpg";//默认logo;

    public static String PROJECT="demo";//项目名

    public static String UPLOAD_PATH;
    @Autowired(required = true)//上传路径
    public void setUploadPath(@Value("${demo.upload.path}")String uploadPath){
        Const.UPLOAD_PATH=uploadPath;
    }
    public static String DOWNLOAD_PATH;
    @Autowired(required = true)//上传路径
    public void setDownloadPath(@Value("${demo.download.path}")String downloadPath){
        Const.DOWNLOAD_PATH=downloadPath;
    }

    public static String IMAGE_PATH="images";//图片存放路径

    public static String USER_AGENT="admin";// 用户代理




}
