package com.cathaybk.member.rest.sso;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "ReturnData")
@XmlAccessorType(XmlAccessType.FIELD)
public class SSOOutputVO implements Serializable {

    public SSOOutputVO() {
        super();
    }

    @XmlElement(name = "ReturnCode")
    private String returncode;

    @XmlElement(name = "ReturnMessage")
    private String returnmessage;

    @XmlElement(name = "EmpId")
    private String empId;

    @XmlElement(name = "EmpName")
    private String empname;

    @XmlElement(name = "BranchId")
    private String branchid;

    @XmlElement(name = "BranchName")
    private String branchname;

    @XmlElement(name = "Class")
    private String role;

    private String apId;

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnmessage() {
        return returnmessage;
    }

    public void setReturnmessage(String returnmessage) {
        this.returnmessage = returnmessage;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAPID() {
        return apId;
    }

    public void setAPID(String apId) {
        this.apId = apId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReturnCode：").append(returncode);
        sb.append("、ReturnMessage：").append(returnmessage);
        sb.append("、EmpId：").append(empId);
        sb.append("、EmpName：").append(empname);
        sb.append("、BranchId：").append(branchid);
        sb.append("、BranchName：").append(branchname);
        sb.append("、Role：").append(role);
        sb.append("、APID：").append(apId);
        return sb.toString();
    }
}
