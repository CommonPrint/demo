package com.demo.service.google_cloud;

import com.demo.entity.google_cloud.InputFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<InputFile> uploadFiles(MultipartFile[] files);
}
