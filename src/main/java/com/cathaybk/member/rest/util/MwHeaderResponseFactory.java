package com.cathaybk.member.rest.util;

import org.springframework.stereotype.Component;

import com.cathaybk.member.rest.dto.MwHeader;
import com.cathaybk.member.rest.dto.MwHeaderResponse;
import com.cathaybk.member.rest.enums.ReturnCodeAndDescEnum;

@Component
public class MwHeaderResponseFactory {

    public MwHeaderResponse genNormalMwHeaderResponse() {
        return genNormalMwHeaderResponse(null);
    }

    public MwHeaderResponse genRecordEmptyMwHeaderResponse() {
        return genRecordEmptyMwHeaderResponse(null);
    }

    public MwHeaderResponse genRequestErrorMwHeaderResponse() {
        return genRequestErrorMwHeaderResponse(null);
    }
    
    public MwHeaderResponse genErrorMwHeaderResponse() {
        return genErrorMwHeaderResponse(null);
    }
    
    public MwHeaderResponse genNormalMwHeaderResponse(MwHeader mwHeader) {

        MwHeaderResponse mwHeaderResponse = new MwHeaderResponse();

        setGerneralAtrribute(mwHeaderResponse, mwHeader);
        setRerurnCodeAndReturnDesc(mwHeaderResponse, ReturnCodeAndDescEnum.CU0000.getValue(), ReturnCodeAndDescEnum.CU0000.getText());

        return mwHeaderResponse;
    }

    public MwHeaderResponse genRecordEmptyMwHeaderResponse(MwHeader mwHeader) {

        MwHeaderResponse mwHeaderResponse = new MwHeaderResponse();

        setGerneralAtrribute(mwHeaderResponse, mwHeader);
        setRerurnCodeAndReturnDesc(mwHeaderResponse, ReturnCodeAndDescEnum.CU0001.getValue(), ReturnCodeAndDescEnum.CU0001.getText());

        return mwHeaderResponse;
    }

    public MwHeaderResponse genRequestErrorMwHeaderResponse(MwHeader mwHeader) {

        MwHeaderResponse mwHeaderResponse = new MwHeaderResponse();

        setGerneralAtrribute(mwHeaderResponse, mwHeader);
        setRerurnCodeAndReturnDesc(mwHeaderResponse, ReturnCodeAndDescEnum.CU2001.getValue(), ReturnCodeAndDescEnum.CU2001.getText());

        return mwHeaderResponse;
    }

    public MwHeaderResponse genErrorMwHeaderResponse(MwHeader mwHeader) {

        MwHeaderResponse mwHeaderResponse = new MwHeaderResponse();

        setGerneralAtrribute(mwHeaderResponse, mwHeader);
        setRerurnCodeAndReturnDesc(mwHeaderResponse, ReturnCodeAndDescEnum.CU9999.getValue(), ReturnCodeAndDescEnum.CU9999.getText());

        return mwHeaderResponse;
    }

    private void setGerneralAtrribute(MwHeaderResponse mwHeaderResponse, MwHeader mwHeader) {
        if (mwHeader != null) {
            mwHeaderResponse.setMsgid(mwHeader.getMsgid());
            mwHeaderResponse.setSourceChannel(mwHeader.getSourceChannel());
            mwHeaderResponse.setTxnseq(mwHeader.getTxnseq());
        }
    }

    private void setRerurnCodeAndReturnDesc(MwHeaderResponse mwHeaderResponse, String code, String desc) {
        mwHeaderResponse.setReturnCode(code);
        mwHeaderResponse.setReturnDesc(desc);
    }

}
