package com.ls.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    void handlerMultipartFile(MultipartFile multipartFile , String unid, HttpServletResponse response);
}
