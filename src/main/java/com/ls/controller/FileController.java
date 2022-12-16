package com.ls.controller;

import com.ls.exception.BusinessException;
import com.ls.exception.DataResult;
import com.ls.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/flie")
public class FileController {

    protected static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileService fileService;
    @PostMapping("/uploads")
    @CrossOrigin
    public void uploads(@RequestParam("file") MultipartFile file, HttpServletResponse response){
        String unid = UUID.randomUUID().toString();
        //String unid="444444";
            logger.info("==>uuid: " + unid);
            if (file == null) {
                logger.error("==>  没有上传文件。");
                throw new BusinessException("没有上传文件");
            }
            logger.info("==>文件名: " + file.getOriginalFilename());
        fileService.handlerMultipartFile(file, unid,response);

    }
}