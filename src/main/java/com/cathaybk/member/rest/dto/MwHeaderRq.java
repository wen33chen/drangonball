package com.cathaybk.member.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MwHeaderRq {
    
    @JsonProperty("RETURNCODE")
    private String returnCode;
    
    @JsonProperty("TXNSEQ")
    private String Txnseq;
    
    
}
