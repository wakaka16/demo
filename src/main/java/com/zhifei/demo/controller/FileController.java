package com.zhifei.demo.controller;

import com.zhifei.demo.common.bean.ResponseBean;
import com.zhifei.demo.common.utils.FileUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController extends BaseController{

  /**
   * 文件上传
   */
  @PostMapping("/upload")
  public ResponseBean upload(HttpServletRequest request) throws IOException {
    String servicePath = "/shoot";
    FileUtil.upload(request, servicePath, false);
    return getResponseBean();
  }

  @GetMapping("/download")
  public String Download(HttpServletResponse response,@RequestParam(value = "fileName")String fileName) {
    String result = FileUtil.downLoadFormApp(fileName, response);
    return result;
  }

}
