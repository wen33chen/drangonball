package com.cathaybk.member.rest.auditlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.cathaybk.member.rest.dto.UserObject;

@Component
@RequestScope
public class AuditLog {

    public static final String delimiter = "$^$";

    private static final Logger securityLog = LogManager.getLogger("securityLog");

    @Autowired
    private UserObject user;

    @Value("${auditLog.system.name}")
    private String systemName;

    private String userID;

    private String userIP;

    private String logTime;

    private ActionType actionType;

    private String appName;

    private AccessObjectType accessObjectType;

    private String keyColumn;

    private String companyID;

    private String opUnit;

    private String empNO;

    private String role;

    private int successCount;

    private String message;

    private String dataBeforeUpdate;

    private String dataAfterUpdate;

    private String sql;

    private AuditLogUseCase auditLogUseCase;

    public void writeAuditLog(String beanName, String actionName, String returnCode) {

        StringBuilder sb = new StringBuilder();

        String empId = user.getEmpId();

        userID = empId;

        userIP = user.getIp();

        companyID = "2";

        opUnit = user.getBranchId();

        logTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        if (StringUtils.isBlank(empId)) {
            empNO = "";
        } else {
            empNO = StringUtils.leftPad(empId, 5, '0');
        }

        HashMap<String, Integer> roles = user.getRoles();
        setRole(roles);

        sb.append(beanName);
        sb.append('/').append(actionName);
        appName = sb.toString();
        sb.setLength(0);

        actionType = auditLogUseCase.getActionType();
        accessObjectType = auditLogUseCase.getAccessObjectType();
        keyColumn = auditLogUseCase.getKeyColumn();
        setMessage("0000".equals(returnCode));

        securityLog.info(this.toString());
    }

    public String getUserID() {
        return userID;
    }

    public String getUserIP() {
        return userIP;
    }

    public String getLogTime() {
        return logTime;
    }

    public String getSystemName() {
        return systemName;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getAppName() {
        return appName;
    }

    public AccessObjectType getAccessObjectType() {
        return accessObjectType;
    }

    public void setAccessObjectType(AccessObjectType accessObjectType) {
        this.accessObjectType = accessObjectType;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = formatInputString(keyColumn);
    }

    public String getCompanyID() {
        return companyID;
    }

    public String getOpUnit() {
        return opUnit;
    }

    public String getEmpNO() {
        return empNO;
    }

    public String getRole() {
        return role;
    }

    public void setRole(HashMap<String, Integer> roles) {
        if ((roles == null) || (roles.isEmpty())) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (String role : roles.keySet()) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(role);
        }

        this.role = sb.toString();
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(boolean isSuccess) {
        message = (isSuccess ? "Success" : "Failure");
    }

    public String getDataBeforeUpdate() {
        return dataBeforeUpdate;
    }

    public void setDataBeforeUpdate(String dataBeforeUpdate) {
        this.dataBeforeUpdate = formatInputString(dataBeforeUpdate, 200);
    }

    public String getDataAfterUpdate() {
        return dataAfterUpdate;
    }

    public void setDataAfterUpdate(String dataAfterUpdate) {
        this.dataAfterUpdate = formatInputString(dataAfterUpdate, 200);
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = formatInputString(sql, 1000);
    }

    private String formatInputString(String inputString) {
        return formatInputString(inputString, -1);
    }

    private String formatInputString(String inputString, int maxLength) {
        if (StringUtils.isNotBlank(inputString)) {
            inputString = inputString.replace("\r\n", " ");
        }

        if (maxLength > 0) {
            inputString = StringUtils.substring(inputString, 0, maxLength);
        }

        return inputString;
    }

    public void setAuditLogUseCase(AuditLogUseCase auditLogUseCase) {
        this.auditLogUseCase = auditLogUseCase;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        //        80847$^$127.0.0.1$^$2019-06-13 10:53:29$^$W060220$^$

        appendColumn(userID, sb);

        appendColumn(userIP, sb);

        appendColumn(logTime, sb);

        appendColumn(systemName, sb);

        //        Q$^$INVFI0_0110/query0108/境外基金$^$T$^$NNNNNNYY$^$

        appendColumn(actionType, sb);

        appendColumn(appName, sb);

        appendColumn(accessObjectType, sb);

        appendColumn(keyColumn, sb);

        //        2$^$00083$^$80847$^$6,RLTEST01,RLTEST02$^$61$^$Success$^$$^${}$^$select * from IVTLXIVP01FUND.FUNCTION_AUTH

        appendColumn(companyID, sb);

        appendColumn(opUnit, sb);

        appendColumn(empNO, sb);

        appendColumn(role, sb);

        appendColumn(Integer.valueOf(successCount), sb);

        appendColumn(message, sb);

        appendColumn(dataBeforeUpdate, sb);

        appendColumn(dataAfterUpdate, sb);

        appendColumn(sql, sb);

        return sb.toString();
    }

    public static enum ActionType {
        Add("A"), Delete("D"), Edit("E"), Query("Q"), Transfer("T"), Report("R"), Output("O"), Print("P");

        private String value;

        private ActionType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String toString() {
            return value;
        }
    }

    public static enum AccessObjectType {
        Table("T"), View("V"), StoredProcedure("P"), Other("O"), Macro("M");

        private String value;

        private AccessObjectType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String toString() {
            return value;
        }
    }

    private void appendColumn(Object value, StringBuilder sb) {
        if (sb.length() > 0) {
            sb.append("$^$");
        }

        if (value == null) {
            return;
        }

        if (!String.class.isInstance(value)) {
            sb.append(value);
            return;
        }

        String str = StringUtils.replace(value.toString(), "\r\n", " ");
        str = StringUtils.replace(str, "\n", " ");
        str = StringUtils.replace(str, "\r", " ");
        sb.append(str);
    }
}
