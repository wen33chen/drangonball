package com.cathaybk.member.rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cathaybk.member.rest.repo.ApiRepo;
import com.cathaybk.member.rest.repo.EmpRoleRepo;
import com.cathaybk.member.rest.svc.FileService;
import com.cathaybk.member.rest.svc.RoleFeatureService;

@RestController
public class MemberController {

    @Autowired
    EmpRoleRepo EmpRoleRepo;

    @Autowired
    ApiRepo EaRepo;
  
    @Autowired
    RoleFeatureService RoleFeatureService;

    @Autowired
    FileService fileService;

    @Value("${file.a0.maximum.size}")
    private String fileMaxSize;

    /**
     * 取得properties檔中定義的上傳檔案大小限制
     * @author NT82980
     * @return
     */
    @RequestMapping(value = "/getFileSize", method = RequestMethod.POST, produces = { "application/json" })
    public String setInitMaxFileSize() {
        return fileMaxSize;
    }

    @RequestMapping(value = "/getpdf", method = RequestMethod.POST, produces = { MediaType.APPLICATION_PDF_VALUE })
    public byte[] getPDF() throws IOException, InterruptedException {
        // convert JSON to Employee 

        // generate the file

        // retrieve contents of "C:/tmp/report.pdf" that were written in showHelp

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        //new StreamContent(new FileStream("D:/JCConf 2018 .pdf", FileMode.Open, FileAccess.Read)); 

        File file = new File("D:/國泰世華銀行新中英文職稱對照表_201301.pdf");
        @SuppressWarnings("unused")
        byte[] fileContent = Files.readAllBytes(file.toPath());
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        @SuppressWarnings("resource")
        FileInputStream fis = new FileInputStream(new File("D:/國泰世華銀行新中英文職稱對照表_201301.pdf"));
        byte[] targetArray = new byte[fis.available()];
        fis.read(targetArray);
        return targetArray;

        //  return new InputStreamResource(pdfFile.getInputStream());
    }
}
