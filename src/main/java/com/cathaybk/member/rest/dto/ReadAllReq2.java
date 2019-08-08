package com.cathaybk.member.rest.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadAllReq2 {

    @Valid
    @NotNull
    @JsonProperty("Header")
    private MwHeaderRq mwheaderRq;

    @JsonProperty("Body")
    private A00400ModifyData tranrq;

    public MwHeaderRq getMwheaderRq() {
        return mwheaderRq;
    }

    public void setMwheaderRq(MwHeaderRq mwheaderRq) {
        this.mwheaderRq = mwheaderRq;
    }

    public A00400ModifyData getTranrq() {
        return tranrq;
    }

    public void setTranrq(A00400ModifyData tranrq) {
        this.tranrq = tranrq;
    }

}
