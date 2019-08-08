package com.cathaybk.member.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MwHeader {

    @JsonProperty("MSGID")
    protected String msgid;

    @JsonProperty("SOURCECHANNEL")
    protected String sourceChannel;

    @JsonProperty("TXNSEQ")
    protected String txnseq;

    @JsonProperty("RETURNCODE")
    protected String returnCode;

    @JsonProperty("RETURNMESSAGE")
    protected String returnMessage;

    public MwHeader() {
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getTxnseq() {
        return txnseq;
    }

    public void setTxnseq(String txnseq) {
        this.txnseq = txnseq;
    }

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the returnMessage
     */
    public String getReturnMessage() {
        return returnMessage;
    }

    /**
     * @param returnMessage the returnMessage to set
     */
    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

}
