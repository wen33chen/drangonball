package com.cathaybk.member.rest.sso;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@SuppressWarnings("serial")
@XmlRootElement(name = "SendData")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ SSOInputVO.class })
public class SSOInputVO implements Serializable {

    public SSOInputVO(String appname, String ip, String token, String empid) {
        super();
        this.token = token;
        this.appname = appname;
        this.empid = empid;
        this.ip = ip;
    }

    public SSOInputVO() {
        super();
    }

    @XmlElement(name = "Token")
    private String token;

    @XmlElement(name = "AppName")
    private String appname;

    @XmlElement(name = "EmpId")
    private String empid;

    @XmlElement(name = "IP")
    private String ip;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Token：").append(token);
        sb.append("、AppName：").append(appname);
        sb.append("、EmpId：").append(empid);
        sb.append("、ApIp：").append(ip);
        return sb.toString();
    }

}
