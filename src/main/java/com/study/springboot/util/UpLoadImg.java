package com.study.springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Slf4j
public class UpLoadImg {
    /**
     * MultipartFile 自动封装上传过来的文件
     * @param headerImg
     * @return
     */
    public String upload(MultipartFile headerImg) throws IOException {

        log.info("上传的信息：headerImg={}",
                headerImg.getSize());
        String originalFilename = null;
        if(!headerImg.isEmpty()){
            //保存到文件服务器，OSS服务器
            originalFilename = headerImg.getOriginalFilename();
            headerImg.transferTo(new File("D:\\cache\\"+originalFilename));
        }

//        if(photos.length > 0){
//            for (MultipartFile photo : photos) {
//                if(!photo.isEmpty()){
//                    originalFilename = photo.getOriginalFilename();
//                    photo.transferTo(new File("D:\\cache\\"+originalFilename));
//                }
//            }
//        }
        return "D:\\cache\\"+originalFilename;
    }
}
