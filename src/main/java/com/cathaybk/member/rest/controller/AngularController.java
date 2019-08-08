package com.cathaybk.member.rest.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cathaybk.member.rest.dto.A00400ModifyData;
import com.cathaybk.member.rest.dto.MwHeader;
import com.cathaybk.member.rest.dto.ReadAllReq2;
import com.cathaybk.member.rest.entity.Cars;
import com.cathaybk.member.rest.entity.EmpData;
import com.cathaybk.member.rest.entity.MenuTreeAuth;
import com.cathaybk.member.rest.entity.TCustomer;
import com.cathaybk.member.rest.repo.EmpDataRepo;
import com.cathaybk.member.rest.repo.MenutreeAuthRepo;
import com.cathaybk.member.rest.repo.TCustomerRepo;
import com.cathaybk.member.rest.svc.CarsService;
import com.cathaybk.member.rest.svc.FileService;

/**
 * @author NT83392
 *
 */
@RestController
public class AngularController {

    /** log */
    private static final Logger LOGGER = LoggerFactory.getLogger(AngularController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private TCustomerRepo tCustomerRepo;

    @Autowired
    private CarsService carsService;

    @Autowired
    private MenutreeAuthRepo menutreeAuthRepo;

    @Autowired
    private EmpDataRepo empDataRepo;

    /**
     * upload
     * @param request
     * @param file
     * @param uploadname
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/files", method = RequestMethod.POST, produces = { "application/json" })
    public Map<String, Object> handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile[] file,
            @RequestParam("uploadname") String uploadname) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("returnMessage", fileService.storeFile(file));
        return map;
    }

    /**
     * downloadFile
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public String downloadSingleFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        // 獲取指定目錄下的第一個檔案
        File scFileDir = new File("D://music_eg");
        File trxFiles[] = scFileDir.listFiles();
        LOGGER.info(trxFiles[0].toString());
        String fileName = trxFiles[0].getName(); //下載的檔名

        // 如果檔名不為空，則進行下載
        if (fileName != null) {
            //設定檔案路徑
            String realPath = "D://music_eg/";
            File file = new File(realPath, fileName);

            // 如果檔名存在，則進行下載
            if (file.exists()) {

                // 配置檔案下載
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下載檔案能正常顯示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 實現檔案下載
                byte[] buffer = new byte[1024];
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    LOGGER.info("Download successfully!");
                } catch (Exception e) {
                    LOGGER.info("Download failed!");
                }
            }
        }
        return fileName;
    }

    /**
     *  目前api調用時第一個參數需要為HttpServletRequest request/@RequestHeader(value = "Authorization") String token 
     *  讓ControllerLogAspect在取得token時可以抓取到
     */
    @PostMapping(value = "/DEMOA00400/modifyData")
    public void modifyD4Data(HttpServletRequest request, @RequestBody ReadAllReq2 tranrq) {
        A00400ModifyData modifyData = (A00400ModifyData) tranrq.getTranrq();
        LOGGER.debug("MODE:{}", modifyData.getMode());
        LOGGER.debug("ROLE:{}", modifyData.getRole());
        LOGGER.debug("MenuCode:{}", modifyData.getMenucode());

        MenuTreeAuth data = new MenuTreeAuth();
        data.setRole(modifyData.getRole());
        data.setMenuCode(modifyData.getMenucode());

        if ("new".equals(modifyData.getMode())) {
            menutreeAuthRepo.save(data);
        } else if ("delete".equals(modifyData.getMode())) {
            menutreeAuthRepo.delete(data);
        }

    }

    @RequestMapping(value = "/getmanu", method = RequestMethod.POST, produces = { "application/json" })
    public ResponseEntity<List<Cars>> getmanu(HttpServletRequest request) {

        return new ResponseEntity<>(carsService.getManu(), HttpStatus.OK);
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/testCase4", method = RequestMethod.POST, produces = { "application/json" })
    public ResponseEntity<String> testCase4(HttpServletRequest request) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/getEmps", method = RequestMethod.POST, produces = { "application/json" })
    public ResponseEntity<List<EmpData>> getEmps(HttpServletRequest request) {
        return new ResponseEntity<>(empDataRepo.findAll(), HttpStatus.OK);
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/getcar", method = RequestMethod.POST, produces = { "application/json" })
    public ResponseEntity<List<Cars>> getCars(HttpServletRequest request) {
        return new ResponseEntity<>(carsService.findAll(), HttpStatus.OK);
    }
    
    /**
     * @param request
     * @return
     */

    @RequestMapping(value = "/getcusts", method = RequestMethod.POST, produces = { "application/json" })
    public ResponseEntity<List<TCustomer>> getCusts(HttpServletRequest request) {
        return new ResponseEntity<>(tCustomerRepo.findAll(), HttpStatus.OK);
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/topicList", method = RequestMethod.POST, produces = { "application/json" })
    public Object getTopicList(HttpServletRequest request) {
        return new ResponseEntity<>(empDataRepo.findAll(), HttpStatus.OK);
    }

    /**
     * getdata
     * @param request
     * @return
     */
    @RequestMapping(value = "/DEMOA00400/getData", method = RequestMethod.POST, produces = { "application/json" })
    public Object getD4Data(HttpServletRequest request) {

        List<Map<String, Object>> finalList = new ArrayList<>();

        List<String> roles = new ArrayList<>();

        for (MenuTreeAuth mta : menutreeAuthRepo.findAll()) {
            String role = String.valueOf(mta.getRole());
            if (!roles.contains(role)) {
                roles.add(role);
            }
        }

        for (int i = 0; i < roles.size(); i++) {
            List<String> authMenuCodeList = new ArrayList<>();
            List<MenuTreeAuth> listMta = menutreeAuthRepo.findByRole(roles.get(i));
            for (int j = 0; j < listMta.size(); j++) {
                MenuTreeAuth mta = listMta.get(j);
                authMenuCodeList.add(mta.getMenuCode());
            }
            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("roleCode", roles.get(i));
            tmpMap.put("menuCodes", authMenuCodeList);
            finalList.add(tmpMap);
        }

        return finalList;
    }

}
