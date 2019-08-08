package com.cathaybk.member.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class MwHeaderResponse extends MwHeader {

    @ApiModelProperty(value = "處理結果代碼")
    @JsonProperty("RETURNCODE")
    protected String returnCode;

    @ApiModelProperty(value = "處理結果訊息")
    @JsonProperty("RETURNDESC")
    protected String returnDesc;

    public MwHeaderResponse() {
        super();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

}
