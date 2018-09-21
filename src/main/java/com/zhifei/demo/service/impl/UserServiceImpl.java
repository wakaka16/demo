package com.zhifei.demo.service.impl;

import com.zhifei.demo.common.bean.BusinessException;
import com.zhifei.demo.common.bean.Const;
import com.zhifei.demo.common.utils.FileUtil;
import com.zhifei.demo.entity.User;
import com.zhifei.demo.mapper.UserMapper;
import com.zhifei.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer register(User user, HttpServletRequest request) {
        //数据校验
        boolean flag = false;
        if(flag){
            throw new BusinessException("数据异常");
        }
        //校验是否已注册
        List<User> users = userMapper.queryByName(user);
        if(!users.isEmpty()){
            throw new BusinessException("该用户名已存在!");
        }
        //上传头像
        String servicePath =Const.PROJECT+"/"+Const.UPLOAD_PATH+"/"+Const.IMAGE_PATH;//上传路径位置
        String saveFilePath = Const.BASE_PATH + "/" + Const.DEFAULT_LOGO;//本地默认图片路径
        user.setLogo(saveFilePath);//设置默认图片路径
        try {
            saveFilePath = FileUtil.upload(request, servicePath, true);
        } catch (IOException e) {
            throw new BusinessException("图片上传失败!");
        }
        // 更换图片路径
        if (!"".equals(saveFilePath)) {
            user.setLogo(saveFilePath);//设置图片路径
        }
        //存储数据
        Integer rows = userMapper.insert(user);
        if(1!=rows){
            throw new BusinessException("用户添加失败!");
        }
        return rows;
    }

    @Override
    public User login (User user) throws BusinessException{
        User loginUser = userMapper.queryByNameAndPwd(user);
        if(null==loginUser){
            throw new BusinessException("用户名或密码错误");
        }
        return loginUser;
    }

    public User cookieLogin(Long userId){
        User result = userMapper.queryByUserId(userId);
        return result;
    }
}
