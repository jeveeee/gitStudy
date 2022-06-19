package com.ideamerry.repair.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadImageUtil {

    public static String thumbnailUploadImage(String prefix, MultipartFile file, int width, int height, String uploadPath, String realUploadPath) {
        String des = realUploadPath + "/" + prefix + file.getOriginalFilename();//缩略图实际存储路径
        File file1 = new File(realUploadPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        try {
            Thumbnails.of(file.getInputStream()).size(width, height).toFile(des);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadPath + "/" + prefix + file.getOriginalFilename();
    }

}
