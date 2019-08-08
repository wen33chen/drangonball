package com.cathaybk.member.rest.controller.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cathaybk.member.rest.dto.ReadAllReq;
import com.cathaybk.member.rest.dto.ResponseTemplate;
import com.cathaybk.member.rest.dto.UserObject;
import com.cathaybk.member.rest.entity.NgbuttonAuth;
import com.cathaybk.member.rest.dto.base.SwitchUser;
import com.cathaybk.member.rest.dto.base.SwitchUserTranrs;
import com.cathaybk.member.rest.repo.FunctionAuthRepo;
import com.cathaybk.member.rest.repo.NgButtonAuthRepo;
import com.cathaybk.member.rest.sso.UserObjectUtil;
import com.cathaybk.member.rest.svc.ApiService;
import com.cathaybk.member.rest.svc.BaseService;
import com.cathaybk.member.rest.svc.JsontokenService;

@RestController
public class BaseController {

    /** log */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private JsontokenService jsontokenService;

    @Autowired
    private FunctionAuthRepo functionAuthRepo;

    @Autowired
    private NgButtonAuthRepo ngButtonAuthRepo;

    @Autowired
    private ApiService apiservice;

    @Autowired
    private UserObject user;//注入登入人員資訊

    @Autowired
    private BaseService baseService;

    /**
     * 
     * @param userID
     * @param token
     * @return
     */
    @RequestMapping(value = "/ssoEndPoint", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public Object doSsoCheck(@RequestParam("UserID") String userID, @RequestParam("Token") String token) {

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("empId", user.getEmpId());
        System.out.println(user.getEmpId() + "驗證完成,Role=" + user.getRole());
        return resultMap;

        //TODO
        // 是否須將登入紀錄紀錄至DB?
    }

    /**
     * 查詢該session下使用者的基本資料
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUserProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getUserProfile(HttpServletRequest request) {

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("EmpNo", user.getEmpId());
        resultMap.put("EmpName", user.getEmpName().trim());
        resultMap.put("DeptCode", user.getBranchId().trim());
        resultMap.put("DeptName", user.getBranchName().trim());
        //resultMap.put("role", user.getRole());

        return resultMap;
    }

    /**
     * 取得本機switch user
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/getSwitchUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseTemplate<SwitchUserTranrs> getSwitchUser() throws IOException {
        return baseService.getSwitchUser();
    }

    /**
     * logout
     * @param request
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void logout(HttpServletRequest request) {

        //TODO 是否需要寫登入登出紀錄進DB
        HttpSession session = request.getSession();
        LOGGER.debug("端口= {} sessionId= {}", request.getLocalPort(), session.getId());
        session.invalidate();
    }

    /**
     * 82758
     * @param request
     * @return
     */
    @RequestMapping(value = "/apimapping", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> apiMapping(HttpServletRequest request) {
        //return EaRepo.findAllByOrderByFunctionId();
        return apiservice.getApiMapping();
    }

    @RequestMapping(value = "/roleFeature", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, List<String>> roleFeature(HttpServletRequest request) {
        List<NgbuttonAuth> buttonauthList = ngButtonAuthRepo.findByRole(user.getRole());//RoleFeatureRepo.findAll();
        Map<String, List<String>> buttonAuthMap = new HashMap();
        for (int i = 0; i < buttonauthList.size(); i++) {
            NgbuttonAuth buttonAuth = buttonauthList.get(i);
            if (!buttonAuthMap.containsKey(buttonAuth.getMenucode())) {
                List<String> stringList = new ArrayList<>();
                stringList.add(buttonAuth.getButtonId());
                buttonAuthMap.put(buttonAuth.getMenucode(), stringList);
            } else {
                buttonAuthMap.get(buttonAuth.getMenucode()).add(buttonAuth.getButtonId());
            }
        }
        return buttonAuthMap;//RoleFeatureRepo.findAll();
    }

    /**
     * 82758
     * @param user
     * @return
     */
    @RequestMapping(value = "/validtoken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String controller(ReadAllReq user) {

        return jsontokenService.getJwttoken(user.getTranrq().getUsername());

    }

    @RequestMapping(value = "/gettoken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> gettoken(@RequestHeader(value = "Authorization") String userAgent, @RequestBody ReadAllReq user) {
        //TODO
        //sessionService.setattr();
        Map<String, Object> map = new HashMap<>();
        map.put("a", jsontokenService.getJwttoken(user.getTranrq().getUsername()));
        return map;
    }
}
