package com.cathaybk.member.rest.advice;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cathaybk.member.rest.auditlog.AuditLog;
import com.cathaybk.member.rest.dto.MwHeader;
import com.cathaybk.member.rest.dto.UserObject;
import com.cathaybk.member.rest.entity.Api;
import com.cathaybk.member.rest.exception.FunctionAuthException;
import com.cathaybk.member.rest.repo.ApiRepo;
import com.cathaybk.member.rest.repo.EmpRoleRepo;
import com.cathaybk.member.rest.repo.FunctionAuthRepo;
import com.cathaybk.member.rest.returncode.Returncode;
import com.cathaybk.member.rest.svc.JsontokenService;

/**
 * 可以自訂義要切入的類別甚至更細的方法，主要用來加log
 * @author NT81652
 *
 */
@Aspect
@Component
@SuppressWarnings({ "rawtypes" })
public class ControllerLogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //    @Pointcut("execution(public * com.cathaybk.xx.rest.controller..*.*(..))")
    //    public void controllerLog() {
    //    }

    @Autowired
    private JsontokenService jsontokenService;

    private final String BEFORE_POINT_CUT2 = "execution(public * com.cathaybk.member.rest.controller.AngularController.* (..)) ";

    @Autowired
    private EmpRoleRepo emproleRepo;

    @Autowired
    private FunctionAuthRepo functionAuthRepo;

    @Autowired
    private ApiRepo apiRepo;

    @Autowired
    private UserObject user;

    @Autowired
    private AuditLog auditLog;

    @Around(BEFORE_POINT_CUT2)
    public Object around(ProceedingJoinPoint pdj) throws Throwable {

        System.out.println("訪問User:" + user.getEmpId() + user.getEmpName());

        Object[] o = pdj.getArgs();
        String test = null;
        //
        //        if (o[0] instanceof String) {
        //            test = (String) o[0];
        //        } else {
        //
        //            HttpServletRequest aaa = (HttpServletRequest) o[0];
        //            test = aaa.getHeader("Authorizatio");
        //        }
        //
        //        boolean validate = jsontokenService.validtoken(test);
        //        if (!validate) {//TODO檢核有錯誤 拋錯
        //            throw new ValidateJsonException();
        //        }

        String name = pdj.getSignature().getName();
        Class<?> claz = pdj.getSignature().getDeclaringType();

        Method method = null;
        for (Method m : claz.getMethods()) {
            if (m.getName().equals(name)) {
                method = m;
            }
        }
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

        //看起來是檢查該role是否有其api權限的!?
        if (requestMapping != null) {
            String[] url = requestMapping.value();
            
            List<Api> apiList = apiRepo.findByUri(url[0]);
            if (apiList.size() > 0) {
                Api api = apiList.get(0);
                String[] roleList=api.getRole().split(";");
                Boolean hasRole=false;
                for(int i=0;i <roleList.length;i++){
                    if(roleList[i].equals(user.getRole())){
                        hasRole=true;
                        break;
                    }
                }
                if(!hasRole){
                    throw new FunctionAuthException();
                }
            }
        }
        //        AuthFeature authFeature = method.getAnnotation(AuthFeature.class);
        //
        //        if (authFeature != null) {
        //
        //            String feature = authFeature.feature();
        //            @SuppressWarnings("unused")
        //            EmpRole empRole = emproleRepo.findByAccount(jsontokenService.getKey(test));//TODO 角色  REDIS
        //
        //            user.getRole();
        //
        //            //FunctionAuth functionAuth = functionAuthRepo.findbyId(empRole.getRole(), feature);//角色是否有feature權限 找不到視為沒有
        //            FunctionAuth functionAuth = functionAuthRepo.findByRoleAndFeature(user.getRole(), feature);//角色是否有feature權限 找不到視為沒有
        //            if (functionAuth == null) {
        //                throw new FunctionAuthException();//TODO 不能登出
        //            }
        //        }

        Object result = pdj.proceed();
        if (result instanceof ResponseEntity) {
            @SuppressWarnings("rawtypes")
            ResponseEntity responseEntity = (ResponseEntity) result;

            HttpHeaders header = new HttpHeaders();
            //          test.set("Access-Control-Allow-Origin", "*");
            //          test.set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            //          test.set("Access-Control-Max-Age", "3600");
            //          test.set("Access-Control-Allow-Headers",
            //          "x-requested-with, authorizatio, test,Content-Type, Authorization, credential, X-XSRF-TOKEN");
            header.set("Access-Control-Expose-Headers", "test");

            //header.set("test", jsontokenService.getJwttokenWithUUID(jsontokenService.getKey(token)));
            MwHeader mwheader = new MwHeader();
            mwheader.setReturnCode(Returncode.OK);
            mwheader.setReturnMessage(Returncode.getMessage(Returncode.OK));
            Map<String, Object> map = new HashMap<>();
            map.put("MWHEADER", mwheader);
            map.put("Body", responseEntity.getBody());

            responseEntity = new ResponseEntity<>(map, header, HttpStatus.OK);

            return responseEntity;
        } else if (result instanceof Map) {
            MwHeader mwheader = new MwHeader();
            mwheader.setReturnCode(Returncode.OK);
            mwheader.setReturnMessage(Returncode.getMessage(Returncode.OK));
            Map<String, Object> map = new HashMap<>();
            map.put("MWHEADER", mwheader);
            map.put("Body", result);
            return map;
        } else {
            return result;
        }

    }

}