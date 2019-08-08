package com.cathaybk.member.rest.svc.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cathaybk.member.rest.dto.EmptyTranrq;
import com.cathaybk.member.rest.dto.MwHeaderResponse;
import com.cathaybk.member.rest.dto.RequestTemplate;
import com.cathaybk.member.rest.dto.ResponseTemplate;
import com.cathaybk.member.rest.dto.base.SwitchUser;
import com.cathaybk.member.rest.dto.base.SwitchUserTranrs;
import com.cathaybk.member.rest.sso.UserObjectUtil;
import com.cathaybk.member.rest.svc.BaseService;
import com.cathaybk.member.rest.util.MwHeaderResponseFactory;

/**
 * 底層服務
 * @author NT81652
 *
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private UserObjectUtil userObjectUtil;

    @Autowired
    private MwHeaderResponseFactory mwHeaderResponseFactory;

    @Override
    public ResponseTemplate<SwitchUserTranrs> getSwitchUser() throws IOException {

        ResponseTemplate<SwitchUserTranrs> response = new ResponseTemplate<>();

        Map<String, String> userMap = userObjectUtil.getSwitchUsers();

        MwHeaderResponse mhr;
        if (userMap.isEmpty()) {
            mhr = mwHeaderResponseFactory.genRecordEmptyMwHeaderResponse();
        } else {
            List<SwitchUser> switchUsers = new ArrayList<>();
            for (Map.Entry<String, String> entry : userMap.entrySet()) {

                SwitchUser switchUser = new SwitchUser();
                switchUser.setEmpId(entry.getKey());
                switchUser.setEmpName(entry.getValue());
                switchUsers.add(switchUser);
            }

            SwitchUserTranrs switchUserTranrs = new SwitchUserTranrs();
            switchUserTranrs.setSwitchUsers(switchUsers);

            mhr = mwHeaderResponseFactory.genNormalMwHeaderResponse();
            response.setTranrs(switchUserTranrs);

        }

        response.setMwHeaderResponse(mhr);
        return response;
    }

}
