package com.cathaybk.member.rest.enums;

public enum ReturnCodeAndDescEnum {

    CU0000("0000", "交易成功"),
    CU0001("0001", "查無資料"),
    CU2001("2001", "資料格式錯誤"),
    CU9001("9001", "JSON格式錯誤"),
    CU9999("9999", "其他系統異常");

    private String value;
    private String text;

    private ReturnCodeAndDescEnum(String value, String text) {

        this.value = value;
        this.text = text;
    }

    public String getValue() {;
        return value;
    }

    public String getText() {
        return text;
    }

}
