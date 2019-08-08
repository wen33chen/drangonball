package com.cathaybk.member.rest.svc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileService {

    private static final String FILE_DIRECTORY = "D:/temp/springFile";

    @Value("${file.a0.maximum.size}")
    private String fileMaxSize;

    public String storeFile(MultipartFile[] file) throws Exception {
        for (int i = 0; i < file.length; i++) {
            String fileName = file[i].getOriginalFilename();
            Path filePath = Paths.get(FILE_DIRECTORY + "/" + fileName);
            long size = file[i].getSize();
            String sizeMax = fileMaxSize;
            if (size > Long.parseLong(sizeMax)) {
                StringBuilder sb = new StringBuilder();
                sb.append("上傳檔案 ").append(fileName).append(" 超過系統大小限制");
                throw new Exception(sb.toString());
            }

            Files.copy(file[i].getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        return "上傳成功";
    }
}