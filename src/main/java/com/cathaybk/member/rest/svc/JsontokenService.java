package com.cathaybk.member.rest.svc;

public interface JsontokenService {

    public String getJwttoken(String userName);

    public Boolean validtoken(String token);
    
    public String getKey(String token);

    public String getJwttokenWithUUID(String uuid);
}
