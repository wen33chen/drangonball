package com.cathaybk.member.rest.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadAllReq {

    @Valid
    @NotNull
    @JsonProperty("MWHEADER")
    private MwHeaderRq mwheaderRq;

    @JsonProperty("TRANRQ")
    private ReadAllTranRq tranrq;

    public MwHeaderRq getMwheaderRq() {
        return mwheaderRq;
    }

    public void setMwheaderRq(MwHeaderRq mwheaderRq) {
        this.mwheaderRq = mwheaderRq;
    }

    public ReadAllTranRq getTranrq() {
        return tranrq;
    }

    public void setTranrq(ReadAllTranRq tranrq) {
        this.tranrq = tranrq;
    }

}
