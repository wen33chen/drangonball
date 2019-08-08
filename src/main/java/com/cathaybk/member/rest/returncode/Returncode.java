package com.cathaybk.member.rest.returncode;

import java.util.Hashtable;

public class Returncode {
    public static final String OK = "0000";

    public static final String ERROR = "9999";



    protected static final Hashtable<String,String> msgDesc = new Hashtable(10);

    static {
        msgDesc.put(OK, "成功");
        msgDesc.put(ERROR, "作業錯誤");
    
    }
    public static String getMessage(String code){
        return msgDesc.get(code);
    }
    
}
