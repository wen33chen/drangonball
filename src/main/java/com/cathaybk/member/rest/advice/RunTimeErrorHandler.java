package com.cathaybk.member.rest.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cathaybk.member.rest.dto.MwHeaderResponse;
import com.cathaybk.member.rest.dto.RequestTemplate;
import com.cathaybk.member.rest.dto.ResponseTemplate;
import com.cathaybk.member.rest.dto.WebRequestContext;
import com.cathaybk.member.rest.exception.FunctionAuthException;
import com.cathaybk.member.rest.exception.ValidateJsonException;
import com.cathaybk.member.rest.util.MwHeaderResponseFactory;

/**
 * 是controller的增強，和ExceptionHandler 一起用來做全局異常
 * @author NT81652
 *
 */
@ControllerAdvice
public class RunTimeErrorHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private WebRequestContext requestContext;
    
    @Autowired
    private MwHeaderResponseFactory mwHeaderResponseFactory;

    @ResponseBody
    @ExceptionHandler(ValidateJsonException.class)
    public ResponseEntity<?> onException(ValidateJsonException e) {

        return new ResponseEntity<>("{}", HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(FunctionAuthException.class)
    public ResponseEntity<?> onFunctionAuthException(FunctionAuthException e) {

        return new ResponseEntity<>("無權限", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseTemplate<?> onException(Exception e) {
        RequestTemplate<?> request = requestContext.getRequest();
        MwHeaderResponse mhr = mwHeaderResponseFactory.genErrorMwHeaderResponse(request.getMwHeader());

        logger.error("API發生錯誤，MSGID : " + mhr.getMsgid(), e);

        ResponseTemplate<?> response = new ResponseTemplate<>();
        response.setMwHeaderResponse(mhr);

        return response;
    }
}
