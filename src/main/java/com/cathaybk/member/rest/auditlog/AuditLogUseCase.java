package com.cathaybk.member.rest.auditlog;

public class AuditLogUseCase {

    private AuditLog.ActionType actionType;

    private AuditLog.AccessObjectType accessObjectType;

    private boolean isAccessField_id = false;

    private boolean isAccessField_name = false;

    private boolean isAccessField_birthday = false;

    private boolean isAccessField_phone = false;

    private boolean isAccessField_address = false;

    private boolean isAccessField_email = false;

    private boolean isAccessField_account = false;

    private boolean isAccessField_cardNumber = false;

    public AuditLogUseCase(AuditLog.ActionType actionType, AuditLog.AccessObjectType accessObjectType) {
        this.actionType = actionType;
        this.accessObjectType = accessObjectType;
    }

    public void setAccessField_id(boolean isAccess) {
        isAccessField_id = isAccess;
    }

    public void setAccessField_name(boolean isAccess) {
        isAccessField_name = isAccess;
    }

    public void setAccessField_birthday(boolean isAccess) {
        isAccessField_birthday = isAccess;
    }

    public void setAccessField_phone(boolean isAccess) {
        isAccessField_phone = isAccess;
    }

    public void setAccessField_address(boolean isAccess) {
        isAccessField_address = isAccess;
    }

    public void setAccessField_email(boolean isAccess) {
        isAccessField_email = isAccess;
    }

    public void setAccessField_account(boolean isAccess) {
        isAccessField_account = isAccess;
    }

    public void setAccessField_cardNumber(boolean isAccess) {
        isAccessField_cardNumber = isAccess;
    }

    public AuditLog.ActionType getActionType() {
        return actionType;
    }

    public AuditLog.AccessObjectType getAccessObjectType() {
        return accessObjectType;
    }

    public String getKeyColumn() {
        StringBuilder sb = new StringBuilder();
        sb.append(isAccessField_id ? 'Y' : 'N');
        sb.append(isAccessField_name ? 'Y' : 'N');
        sb.append(isAccessField_birthday ? 'Y' : 'N');
        sb.append(isAccessField_phone ? 'Y' : 'N');
        sb.append(isAccessField_address ? 'Y' : 'N');
        sb.append(isAccessField_email ? 'Y' : 'N');
        sb.append(isAccessField_account ? 'Y' : 'N');
        sb.append(isAccessField_cardNumber ? 'Y' : 'N');
        return sb.toString();
    }
}
