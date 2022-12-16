package com.ls.service.impl;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.ls.entity.FileInfo;
import com.ls.exception.BusinessException;
import com.ls.exception.ErrorEnum;
import com.ls.service.FileService;
import com.ls.service.IFileInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Service
public class FileServiceImpl implements FileService {

    protected static final Logger logger= LoggerFactory.getLogger(FileService.class);
    @Autowired
    private IFileInfoService iFileInfoService;
    //private String directoryPath = "/usr/local/file";
    private String directoryPath="D:/data/";
    public FileServiceImpl() {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    @Transactional
    public void handlerMultipartFile(MultipartFile multipartFile ,String unid, HttpServletResponse response) {
        String fileOldName = multipartFile.getOriginalFilename();
        int beginIndex = fileOldName.lastIndexOf(".");
        String suffix = fileOldName.substring(beginIndex);
        String newFileName =  unid+ suffix;
        File localFile = new File(directoryPath+newFileName);
        try {
            multipartFile.transferTo(localFile); //把上传的文件保存至本地
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileOldName);
            fileInfo.setFilePath(directoryPath);
            fileInfo.setUuid(unid);
            iFileInfoService.save(fileInfo);
            //File inPath = new File("./test.pdf");
            File outPath = new File(directoryPath+fileOldName.substring(0,beginIndex)+".docx");
            //pdfActiveX PDDoc对象 主要建立PDF对象
            ActiveXComponent app = new ActiveXComponent("AcroExch.PDDoc");
            //PDF控制对象
            Dispatch pdfObject = app.getObject();
            //打开PDF文件，建立PDF操作的开始
            Dispatch.call(pdfObject, "Open", new Variant(localFile.getAbsolutePath()));
            Variant jsObj = Dispatch.call(pdfObject, "GetJSObject");
            Dispatch.call(jsObj.getDispatch(), "SaveAs", outPath.getPath(), "com.adobe.acrobat.docx");
            app.invoke("Close");

            try (InputStream fis = new BufferedInputStream(new FileInputStream(outPath)); OutputStream fos =
                    new BufferedOutputStream(response.getOutputStream())) {
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);

                String fileName=fileOldName.substring(0,beginIndex)+".docx";
                String validFileName = URLEncoder.encode(fileName, "utf-8")
                        .replaceAll("\\+", "%20");

                // 清空response
                response.reset();
                // 设置header
                response.addHeader(HttpHeaders.CONTENT_LENGTH, "" + outPath.length());
                response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
                // 组装contentDisposition的值
                StringBuilder contentDispositionValue = new StringBuilder();
                contentDispositionValue.append("attachment; filename=")
                        .append(validFileName)
                        .append(";")
                        .append("filename*=")
                        .append("utf-8''")
                        .append(validFileName);
                response.setHeader("Content-Disposition",
                        contentDispositionValue.toString());
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Access-Control-Allow-Origin", "*");
                fos.write(buffer);
                fos.flush();
            }catch (Exception e){
                throw new BusinessException(ErrorEnum.E_20012);
            }
        }catch (IOException e){
            e.printStackTrace();
           throw new BusinessException(ErrorEnum.E_20012);
        }
    }

}
