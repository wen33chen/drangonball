package com.cathaybk.member.rest.sso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class UserObjectUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String SWITCH_USERS_PATH = "config/switchUsers";

    /** eBAF session id key */
    public static final String SESSION_KEY = "$coreSys$INITSESSIONID";

    @Autowired
    private SSOProperty ssoProperty;

    private static final Map<String, SSOOutputVO> SWITCH_USERS = Collections.synchronizedMap(new TreeMap<String, SSOOutputVO>());

    /**
     * 取得本機設定
     * @param httpServletRequest
     * @return
     */
    public SSOOutputVO getSSOOutputVOByConfig(final HttpServletRequest httpServletRequest) {

        try {

            if (!"Y".equals(ssoProperty.getLocal())) {
                return null;
            }

            initSwitchUsersCache();

            if (!SWITCH_USERS.isEmpty()) {
                String EMP_ID = httpServletRequest.getParameter(ssoProperty.getSwitchUserRequestAttrName());

                if (StringUtils.isNotBlank(EMP_ID)) {
                    SSOOutputVO ssoOutputVOFromTestUsers = SWITCH_USERS.get(EMP_ID);
                    if (ssoOutputVOFromTestUsers == null) {
                        throw new IllegalArgumentException("");
                    }
                    return ssoOutputVOFromTestUsers;
                }
                return getDefaultUser();
            }

            SSOOutputVO ssoOutputVO = new SSOOutputVO();
            ssoOutputVO.setEmpId(ssoProperty.getEmpId());
            ssoOutputVO.setEmpname(ssoProperty.getEmpName());
            ssoOutputVO.setBranchid(ssoProperty.getBranchId());
            ssoOutputVO.setBranchname(ssoProperty.getBranchName());
            ssoOutputVO.setRole(ssoProperty.getRole());
            ssoOutputVO.setAPID(ssoProperty.getApId());

            return ssoOutputVO;

        } catch (IllegalArgumentException e) {
            logger.error("", e);
            throw e;
        } catch (Exception e) {
            logger.error("", e);
            throw new IllegalAccessError(e.getMessage());
        }

    }

    public Map<String, String> getSwitchUsers() throws IOException {
        if (!"Y".equals(ssoProperty.getLocal())) {
            return null;
        }

        initSwitchUsersCache();
        Map<String, String> switchUsers = new TreeMap<>();
        for (String EMP_ID : SWITCH_USERS.keySet()) {
            SSOOutputVO userVO = SWITCH_USERS.get(EMP_ID);
            switchUsers.put(EMP_ID, userVO.getEmpname());
        }
        return switchUsers;
    }

    private static SSOOutputVO getDefaultUser() {
        Iterator<Map.Entry<String, SSOOutputVO>> iter = SWITCH_USERS.entrySet().iterator();
        return iter.next().getValue();
    }

    private void initSwitchUsersCache() throws IOException {
        if (!SWITCH_USERS.isEmpty()) {
            return;
        }

        synchronized (UserObjectUtil.class) {
            if (!SWITCH_USERS.isEmpty()) {
                return;
            }

            Path path = Paths.get(new ClassPathResource(SWITCH_USERS_PATH).getURI());

            try {
                Properties userProperties = new Properties();
                Files.walkFileTree(path, new FileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.toFile()), "utf-8"))) {
                            userProperties.clear();
                            userProperties.load(br);
                            String EMP_ID = userProperties.getProperty("com.cathaybk.common.sso.util.EmpId");
                            if (StringUtils.isBlank(EMP_ID)) {
                                return FileVisitResult.CONTINUE;
                            }
                            SSOOutputVO ssoOutputVO = new SSOOutputVO();
                            ssoOutputVO.setEmpId(EMP_ID);
                            ssoOutputVO.setEmpname(userProperties.getProperty("com.cathaybk.common.sso.util.EmpName"));
                            ssoOutputVO.setBranchid(userProperties.getProperty("com.cathaybk.common.sso.util.BranchId"));
                            ssoOutputVO.setBranchname(userProperties.getProperty("com.cathaybk.common.sso.util.BranchName"));
                            ssoOutputVO.setRole(userProperties.getProperty("com.cathaybk.common.sso.util.Role"));
                            ssoOutputVO.setAPID(userProperties.getProperty("com.cathaybk.common.sso.util.AP_ID"));
                            SWITCH_USERS.put(EMP_ID, ssoOutputVO);
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                });
            } catch (Exception e) {
                logger.error("取得測試使用者資料失敗");
            }

        }
    }
}
