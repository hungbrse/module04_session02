package com.ra.session03.service.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {
    @Value("${upload-path}")
    String uploadPath;
    @Override
    public String uploadFile(MultipartFile image) {

//        String uploadPath = "C:\\Users\\Amin\\Desktop\\Module_04\\session02\\src\\main\\resources\\uploads\\";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //Lấy tên file
        String fileImage = image.getOriginalFilename();
//        try {
//            image.transferTo(file);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //Copy file upload lên gửi vào thư mục uploads
        try {
            FileCopyUtils.copy(image.getBytes(), new File(uploadPath + File.separator + fileImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileImage;
    }
}
