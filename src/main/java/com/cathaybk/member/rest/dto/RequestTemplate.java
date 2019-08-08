package com.cathaybk.member.rest.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestTemplate<Q> {

    @Valid
    @NotNull
    @JsonProperty("MWHEADER")
    private MwHeader mwHeader;

    @Valid
    @JsonProperty("TRANRQ")
    private Q tranrq;

    public RequestTemplate() {
    }

    public MwHeader getMwHeader() {
        return mwHeader;
    }

    public void setMwHeader(MwHeader header) {
        this.mwHeader = header;
    }

    public Q getTranrq() {
        return tranrq;
    }

    public void setTranrq(Q tranrq) {
        this.tranrq = tranrq;
    }

}
