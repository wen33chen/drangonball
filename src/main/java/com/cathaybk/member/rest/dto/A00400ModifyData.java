package com.cathaybk.member.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class A00400ModifyData extends TranRq {

    @JsonProperty("mode")
    private String mode;

    @JsonProperty("role")
    private String role;

    @JsonProperty("menuCode")
    private String menucode;

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the menucode
     */
    public String getMenucode() {
        return menucode;
    }

    /**
     * @param menucode the menucode to set
     */
    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

}
