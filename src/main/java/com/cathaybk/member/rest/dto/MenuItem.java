package com.cathaybk.member.rest.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 選單的 view model
 * @author NT83392
 *
 */
public class MenuItem {

    /** 選單 ID */
    private int menuId;

    /** 上一層選單的 ID */
    private int parentId;

    /** 選單顯示文字 */
    private Object displayText;

    /** 子選項 */
    private List<MenuItem> subMenuList;

    /** 是否啟用 */
    private Object enabled;

    /** 排序 */
    private BigDecimal sort;

    /** 選單的連結 */
    private Object linkUrl;

    /** 選單的查詢代碼 */
    private Object menuCode;

    /** 功能代碼 */
    private BigDecimal featureId;

    /** 子選單數量 */
    private Object childrenCount;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int l) {
        this.menuId = l;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Object getDisplayText() {
        return displayText;
    }

    public void setDisplayText(Object object) {
        this.displayText = object;
    }

    public List<MenuItem> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<MenuItem> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public Object isEnabled() {
        return enabled;
    }

    public void setEnabled(Object object) {
        this.enabled = object;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal bigDecimal) {
        this.sort = bigDecimal;
    }

    public Object getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(Object object) {
        this.linkUrl = object;
    }

    public Object getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(Object object) {
        this.menuCode = object;
    }

    public BigDecimal getFeatureId() {
        return featureId;
    }

    public void setFeatureId(BigDecimal bigDecimal) {
        this.featureId = bigDecimal;
    }

    public Object getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Object object) {
        this.childrenCount = object;
    }

}
