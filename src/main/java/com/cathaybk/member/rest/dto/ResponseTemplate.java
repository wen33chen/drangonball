package com.cathaybk.member.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseTemplate<S> {

    @JsonProperty("MWHEADER")
    private MwHeaderResponse mwHeaderResponse;

    @JsonProperty("TRANRS")
    private S tranrs;

    public ResponseTemplate() {
        this.mwHeaderResponse = new MwHeaderResponse();
    }

    public MwHeaderResponse getMwHeaderResponse() {
        return mwHeaderResponse;
    }

    public void setMwHeaderResponse(MwHeaderResponse mwHeaderResponse) {
        this.mwHeaderResponse = mwHeaderResponse;
    }

    public S getTranrs() {
        return tranrs;
    }

    public void setTranrs(S tranrs) {
        this.tranrs = tranrs;
    }

}
