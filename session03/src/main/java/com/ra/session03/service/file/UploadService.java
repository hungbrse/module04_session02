package com.ra.session03.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadFile(MultipartFile file);
}
