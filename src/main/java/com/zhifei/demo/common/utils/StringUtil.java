package com.zhifei.demo.common.utils;

import java.util.UUID;

public class StringUtil {
    /**
     * 获取文件类型(文件扩展名,不加dian)
     *
     * @param fileName
     * @return
     */
    public static String getFileExtName(String fileName) {
        if (fileName != null) {
            int i = fileName.lastIndexOf('.');
            if (i > -1) {
                return fileName.substring(i + 1).toLowerCase();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 获取UUID+timestamp
     * @return
     */
    public static String getUUIDTimestamp(){
        String str = UUID.randomUUID().toString().replace("-","")+System.currentTimeMillis();
        return str;
    }


}
