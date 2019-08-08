package com.cathaybk.member.rest.svc;

import java.io.IOException;

import com.cathaybk.member.rest.dto.ResponseTemplate;
import com.cathaybk.member.rest.dto.base.SwitchUserTranrs;

/**
 * 底層服務 interface
 * @author NT81652
 *
 */
public interface BaseService {

    
    public ResponseTemplate<SwitchUserTranrs> getSwitchUser() throws IOException;
    
}
