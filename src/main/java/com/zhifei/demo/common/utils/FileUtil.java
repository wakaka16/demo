package com.zhifei.demo.common.utils;

import com.zhifei.demo.common.bean.Const;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;

/**
 * 文件上传的工具类
 */
public class FileUtil {

  /**
   * 文件上传
   */
  public static String upload(HttpServletRequest request, String servicePath, boolean reName)
      throws IOException {
    String basePath = Const.BASE_PATH + "/file/";//服务器地址
    StringBuilder saveFilePath = new StringBuilder();//数据库储存路径
    // 获取多文件
    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
    Iterator<String> files = multipartHttpServletRequest.getFileNames();
    while (files.hasNext()) {
      // 获取单文件
      MultipartFile file = multipartHttpServletRequest.getFile(files.next());
      // 获取单文件名
      String fileName = file.getOriginalFilename();
      if (reName) {
        String suffix = StringUtil.getFileExtName(fileName);
        fileName = StringUtil.getUUIDTimestamp().concat(".").concat(suffix);
      }
      // 创建文件
      File filePath = new File("/" + Const.UPLOAD_PATH + "/" + servicePath + "/" + fileName);
      if (!filePath.exists()) {
        filePath.mkdirs();
        file.transferTo(filePath);
      }
      //保存文件路径
      saveFilePath.append(basePath + servicePath + "/" + fileName + "|");
    }
    //清除最后一个|
    Integer index = saveFilePath.lastIndexOf("|");
    if (index >= 0) {
      saveFilePath.delete(index, index + 1);
    }
    return saveFilePath.toString();
  }

  /**
   * 下载文件(服务器上)
   */
  public static void downLoad(String fileName, HttpServletResponse response, String servicePath)
      throws IOException {
    File file = new File("/" + Const.UPLOAD_PATH + "/" + servicePath + "/", fileName);//被下载文件
    response.setCharacterEncoding("utf-8");
    response.setContentType("application/octet-stream");//
    response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
    // 获取读取文件的对象
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
    // 获取写文件对象
    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
    //复制
    byte[] bytes = new byte[2048];
    int len = 0;
    while ((len = bis.read(bytes)) != -1) {
      bos.write(bytes, 0, len);
    }
    // 关闭资源
    if (bos != null) {
      bos.close();
    }
    if (bis != null) {
      bis.close();
    }
  }

  /**
   * 下载文件(应用内)
   */
  public static String downLoadFormApp(String fileName, HttpServletResponse response) {
    //防止输出文件名乱码
    String outFileName = null;
    try {
      outFileName = new String(fileName.getBytes(),"ISO8859-1");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    //设置响应头
    response.setCharacterEncoding("utf-8");
    response.setContentType("application/octet-stream");//
    response.addHeader("Content-Disposition", "attachment;fileName=" + outFileName);// 设置文件名
    File path = null;

    try {
      path = new File(ResourceUtils.getURL("classpath:").getPath());//项目路径
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    BufferedInputStream bis = null;
    // 获取读取文件的对象
    try {
      bis = new BufferedInputStream(
          new FileInputStream(new File(path + "/static/download/" + fileName)));
    } catch (FileNotFoundException e) {
      return "系统找不到指定文件";
    }

    // 获取写文件对象
    BufferedOutputStream bos = null;
    try {
      bos = new BufferedOutputStream(response.getOutputStream());
      //复制
      byte[] bytes = new byte[2048];
      int len = 0;
      while ((len = bis.read(bytes)) != -1) {
        bos.write(bytes, 0, len);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // 关闭资源
      try {
        if (bos != null) {
          bos.close();
        }
        if (bis != null) {
          bis.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    return "success";
  }

  public static void main(String[] args) throws FileNotFoundException {
    String path = ResourceUtils.getURL("classpath:").getPath();//classpath大小写有区分
    String fileName ="应用插件安装说明.zip";
    File file = new File(path+"/static/download/"+fileName);
    File file2 = new File(path+"com");
    System.out.println(file.getPath());
    System.out.println(file.exists());
    System.out.println(path);
  }


}
