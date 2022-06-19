package com.ideamerry.repair.api;

import com.ideamerry.repair.entity.FileUploadEntity;
import com.ideamerry.repair.util.DateUtil;
import com.ideamerry.repair.util.UploadImageUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;


/**
 * @author William
 * @detail 图片、音频上传
 * @date 2018年03月29日 10:32
 **/

@RequestMapping("api/upload")
@Controller
public class UploadController {

    @Value("${upload.path}")
    private String uploadPath;

    @RequestMapping(value = "/uploadAudio")
    @ResponseBody
    public FileUploadEntity uploadAudio(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        String imgUrl = "";
        FileUploadEntity fileUploadEntity = new FileUploadEntity();
        String type = file.getOriginalFilename().substring(
                file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
        String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
        String dirName = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
        judeDirExists(new File(uploadPath + "upload/" + dirName));
        imgUrl = "/upload/" + dirName + "/" + filename;
        try {
            //保存文件
            File dest = new File(uploadPath + "upload/" + dirName + "/" + filename);
            file.transferTo(dest); //保存文件
            fileUploadEntity.setUrl(imgUrl);
        } catch (Exception e) {
        }
        return fileUploadEntity;
    }

    /**
     * 上传视频
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
    @ResponseBody
    public FileUploadEntity uploadVideo(
            MultipartFile file,
            HttpServletRequest request) {
        String imgUrl = "";
        FileUploadEntity fileUploadEntity = new FileUploadEntity();
        String type = file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
        String prex = System.currentTimeMillis() + "";
        String filename = prex + type;// 取当前时间戳作为文件名
        String dirName = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
        judeDirExists(new File(uploadPath + "upload/" + dirName));
        try {
            File dest = new File(uploadPath + "upload/" + dirName + "/" + filename);
            file.transferTo(dest); //保存文件
            if (!StringUtils.isEmpty(filename)) {
                if (filename.toLowerCase().endsWith(".mp4") || filename.toLowerCase().endsWith(".wmv")
                        || filename.toLowerCase().endsWith(".flv") || filename.toLowerCase().endsWith(".mov")
                        || filename.toLowerCase().endsWith(".rmvb") || filename.toLowerCase().endsWith(".avi")
                        || filename.toLowerCase().endsWith(".webm") || filename.toLowerCase().endsWith(".rm") || filename.toLowerCase().endsWith(".3gp")) {
                    imgUrl = "/upload/" + dirName + "/" + prex + "/" + prex + ".mp4";
                } else {
                    imgUrl = "/upload/" + dirName + "/" + filename;
                }
            }
            fileUploadEntity.setUrl(imgUrl);
        } catch (Exception e) {
        }
        //转换代码
        return fileUploadEntity;
    }
    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public FileUploadEntity upload(
            @RequestParam("file") MultipartFile[] files,
            HttpServletRequest request) {
        String imgUrl = "";
        FileUploadEntity fileUploadEntity = new FileUploadEntity();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String type = files[i].getOriginalFilename().substring(
                        files[i].getOriginalFilename().indexOf("."));// 取文件格式后缀名
                String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
                String dirName = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
                judeDirExists(new File(uploadPath + "/upload/" + dirName));
                imgUrl = "/upload/" + dirName + "/" + filename;
                try {
                    //根据图片内容判断是否为图片文件
                    InputStream inputStream = files[i].getInputStream();
                    BufferedImage bi = ImageIO.read(inputStream);
                    if (bi != null) {
                        String path = uploadPath + "/upload/" + dirName + "/old-" + filename;// 存放位置
// 保留原图
//                        File destFile = new File(path);
//                        FileUtils.copyInputStreamToFile(files[i].getInputStream(),
//                                destFile);
                        int width = bi.getWidth();
                        int height = bi.getHeight();
                        if (width > 1400) {
                            width = 1400;
                        }
                        if (height > 1400) {
                            height = 1400;
                        }
                        imgUrl = UploadImageUtil.thumbnailUploadImage("new-", files[i], width, height, "/upload/" + dirName,
                                uploadPath + "/upload/" + dirName);
                        fileUploadEntity.setUrl(imgUrl);
                    } else {
                        String path = uploadPath + "/upload/" + dirName + "/" + filename;// 存放位置
                        File destFile = new File(path);
                        FileUtils.copyInputStreamToFile(files[i].getInputStream(),
                                destFile);
                        fileUploadEntity.setUrl(imgUrl);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return null;
        }
        return fileUploadEntity;

    }


    // 判断文件夹是否存在
    public static void judeDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }

    }
}

