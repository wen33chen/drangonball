package com.cathaybk.member.rest.dto.base;

import java.util.List;

/**
 * SwitchUser 回傳 DTO
 * @author NT81652
 *
 */
public class SwitchUserTranrs {

    private List<SwitchUser> switchUsers;

    public List<SwitchUser> getSwitchUsers() {
        return switchUsers;
    }

    public void setSwitchUsers(List<SwitchUser> switchUsers) {
        this.switchUsers = switchUsers;
    }

}
