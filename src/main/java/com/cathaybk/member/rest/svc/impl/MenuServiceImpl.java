package com.cathaybk.member.rest.svc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cathaybk.member.rest.dto.MenuItem;
import com.cathaybk.member.rest.dto.UserObject;
import com.cathaybk.member.rest.entity.MenuTreeAuth;
import com.cathaybk.member.rest.entity.Ngmenutree;
import com.cathaybk.member.rest.entity.NgmenutreeTran;
import com.cathaybk.member.rest.entity.NgmenutreeTranPK;
import com.cathaybk.member.rest.repo.MenutreeAuthRepo;
import com.cathaybk.member.rest.repo.NgmenutreeRepo;
import com.cathaybk.member.rest.repo.NgmenutreeTranRepo;
import com.cathaybk.member.rest.svc.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private NgmenutreeRepo ngMenuTreeRepo;

    @Autowired
    private NgmenutreeTranRepo ngMenutreeTranRepo;

    @Autowired
    private MenutreeAuthRepo menutreeAuthRepo;

    @Override
    public List<MenuItem> getMenuItems(String msgLang, UserObject user) {
        List<String> roleList = new ArrayList<>();
        roleList.add(user.getRole());//從userObject中取的目前登入角色權限

        List<String> authMenuCodes = new ArrayList<>();
        for (MenuTreeAuth mta : menutreeAuthRepo.findByRoleIn(roleList)) {
            authMenuCodes.add(String.valueOf(mta.getMenuCode()));
        }

        // 找出可用的選單(最後一層)
        List<MenuItem> detailList = new ArrayList<>();
        Map<Integer, List<MenuItem>> tmpMap = new HashMap<>();
        for (Ngmenutree mt : ngMenuTreeRepo.findByEnabledAndParentIdNotNullAndMenuCodeIn('Y', authMenuCodes)) {
            MenuItem tmpMi = setMenuItem(mt, msgLang);
            int parentId = mt.getParentId();
            if (!tmpMap.containsKey(parentId)) {
                tmpMap.put(parentId, new ArrayList<>());
            }
            tmpMap.get(parentId).add(tmpMi);
        }

        // 組選單(加入父MenuItem)
        List<Integer> parentKeys = new ArrayList<>(tmpMap.keySet());
        for (int i = 0; i < parentKeys.size(); i++) {
            Optional<Ngmenutree> optionMtItem = ngMenuTreeRepo.findById(parentKeys.get(i));
            if (optionMtItem.isPresent()) {
                Ngmenutree mtItem = optionMtItem.get();
                MenuItem tmpMi = setMenuItem(mtItem, msgLang);
                tmpMi.setSubMenuList(tmpMap.get(mtItem.getMenuId()));
                detailList.add(tmpMi);
            }
        }
        return detailList;
    }

    @Override
    public List<String> getMenuCodesByRole(UserObject user) {
        List<String> roleList = new ArrayList<>();
        roleList.add(user.getRole());//從userObject中取的目前登入角色權限

        List<String> authMenuCodes = new ArrayList<>();
        for (MenuTreeAuth mta : menutreeAuthRepo.findByRoleIn(roleList)) {
            authMenuCodes.add(String.valueOf(mta.getMenuCode()));
        }
        return authMenuCodes;
    }

    /**
     * 將後端資料庫的MenuTree資料轉換成前端可辨認之格式
     * @param mtItem
     * @param msgLang
     * @return
     */
    private MenuItem setMenuItem(Ngmenutree mtItem, String msgLang) {
        MenuItem tmpMi = new MenuItem();
        int menuId = mtItem.getMenuId();
        tmpMi.setChildrenCount(mtItem.getChildrenCount());
        NgmenutreeTranPK transPk = new NgmenutreeTranPK();
        transPk.setMenuId(menuId);
        transPk.setMsgLang(msgLang);
        Optional<NgmenutreeTran> otMenuTran = ngMenutreeTranRepo.findById(transPk);
        if (otMenuTran.isPresent()) {
            tmpMi.setDisplayText(otMenuTran.get().getDisplayText());
        } else {
            tmpMi.setDisplayText(mtItem.getMenuCode());
        }
        tmpMi.setLinkUrl(mtItem.getLinkUrl());
        tmpMi.setMenuCode(mtItem.getMenuCode());
        tmpMi.setMenuId(menuId);
        tmpMi.setSort(mtItem.getSort());
        return tmpMi;
    }

}
