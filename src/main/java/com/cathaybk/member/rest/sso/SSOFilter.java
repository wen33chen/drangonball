package com.cathaybk.member.rest.sso;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cathaybk.member.rest.util.AESHelper;

/**
 * SSO
 * @author NT81652
 *
 */
@Component
@Order(2)
public class SSOFilter implements Filter {

    /** 是否第一次進入 SSOFilter */
    public static final String IS_FISRT_ENTER = "$CathayBK$isFirstEnter";

    private Logger logger = LoggerFactory.getLogger(getClass());

    /** 是否為 debug 模式 */
    private boolean IS_DEBUG = logger.isDebugEnabled();

    /** session id 的前置字元 */
    private static final String UUID_PREFIX = "cub-";

    /** 伺服器編碼 */
    private static final String CHARSET = "UTF-8";

    public static final String INIT_SESSION_ID_COOKIE_NAME = "INITSESSIONID";

    @Autowired
    private SSOProperty ssoProperty;

    @Autowired
    private UserObjectUtil userObjectUtil;

    //login改寫
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String sessionAttrName = ssoProperty.getSessionAttrName();

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if ("Y".equals(ssoProperty.getLocal())) {
            HttpSession session = httpRequest.getSession(false);
            SSOOutputVO ssoOutputVO = null;

            //用於switchUser
            String empId = httpRequest.getParameter(ssoProperty.getSwitchUserRequestAttrName());

            if (session != null) {
                ssoOutputVO = (SSOOutputVO) session.getAttribute(sessionAttrName);

                if (ssoOutputVO == null || StringUtils.isNotBlank(empId)) {
                    // Session失效=>找不到ssoOutputVO，本機測試時須重新產生可用的session
                    logger.debug("*****LocalSessionTimeOut/NotFound*****");
                    ssoOutputVO = userObjectUtil.getSSOOutputVOByConfig(httpRequest);
                    Map<String, String> switchUsers = userObjectUtil.getSwitchUsers();
                    setSsoOutputVOInSessionByConfig(httpRequest, httpResponse, ssoOutputVO, null, switchUsers);
                    httpResponse.setStatus(419);
                    return;
                }
            } else {
                // Session不存在=>直接產生可用的session
                ssoOutputVO = userObjectUtil.getSSOOutputVOByConfig(httpRequest);
                Map<String, String> switchUsers = userObjectUtil.getSwitchUsers();
                setSsoOutputVOInSessionByConfig(httpRequest, httpResponse, ssoOutputVO, null, switchUsers);
                httpResponse.setStatus(419);
                return;
            }
            // 繼續執行request
            chain.doFilter(request, response);
            return;
        }

        String ip = httpRequest.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = httpRequest.getRemoteAddr();
        }

        // TODO isLoadTest?
        boolean isLoadTest = false;

        // 從http參數中取得user id及token
        String token;
        String custAP_ID;

        if (isLoadTest) {
            token = "LoadingTestToken";
            custAP_ID = null;

        } else {

            String param = httpRequest.getParameter("APID_ALIAS_NAME");

            if (param == null) {
                custAP_ID = null;
                token = request.getParameter("Token");

            } else {

                String indexName = "?Token=";
                int keyIndex = param.indexOf(indexName);

                if (keyIndex < 0) {
                    custAP_ID = null;
                    token = request.getParameter("Token");
                } else {
                    token = param.substring(keyIndex + indexName.length());
                    String APID_ALIAS_NAME = param.substring(0, keyIndex);
                    StringBuilder sb = new StringBuilder();
                    sb.append("com.cathaybk.common.sso.util.AP_ID").append('_').append(APID_ALIAS_NAME);
                    //String key = sb.toString();
                    custAP_ID = "W060220";
                }

            }

        }

        SSOOutputVO outVO = null;

        String ebafToken = httpRequest.getParameter("$CathayBK$ebafToken");

        if (ebafToken == null) {
            HttpSession session = httpRequest.getSession(false);

            if (session != null) {

                String sessionTokenKey = ssoProperty.getSessionTokenKey();
                String sessionSSOToken = (String) session.getAttribute(sessionTokenKey);

                if (IS_DEBUG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("token=[").append(token).append("], sessionSSOToken=[").append(sessionSSOToken).append("]");
                    logger.debug(sb.toString());
                }

                if (sessionSSOToken != null && (token == null || "".equals(token) || sessionSSOToken.equals(token))) {
                    outVO = (SSOOutputVO) session.getAttribute(sessionAttrName);
                }

            }
        } else {
            // TODO 從其他Web來的，需自行跟全行取得認證
        }

        if (outVO == null) {
            if (StringUtils.isNotBlank(token)) {
                if (IS_DEBUG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Session is null or session.getAttribute(").append(sessionAttrName).append(") is null.");
                    logger.debug(sb.toString());
                }

                boolean isLoadMember;

                if (isLoadTest) {
                    isLoadMember = true;
                    if (IS_DEBUG) {
                        logger.debug("============================================");
                        logger.debug("ip        => " + ip);
                        logger.debug("============================================");
                    }
                } else {
                    isLoadMember = false;
                }

                boolean loginSuccess = initalizeCubSsoAuthentication(httpRequest, httpResponse, custAP_ID, token, isLoadMember);
                if (loginSuccess) {
                    chain.doFilter(new SSOFilterLoginSuccessServletRequest((HttpServletRequest) request), response);
                } else {
                    //httpResponse.sendRedirect(httpRequest.getContextPath());
                    httpResponse.setStatus(401);
                    return;
                }
            } else {
                if (IS_DEBUG) {
                    logger.debug("============================================");
                    logger.debug("Request URI: " + httpRequest.getRequestURI());
                    logger.debug("Request URL: " + httpRequest.getRequestURL());

                    //logger.debug("sessionErrorPage: " + sessionErrorPage);
                    logger.debug("============================================");
                }
                if (isAjaxRequest(httpRequest)) {
                    httpResponse.addHeader("$ebaf$login$timeout", "true");
                }
                //httpRequest.getRequestDispatcher(sessionErrorPage).forward(httpRequest, httpResponse);
                httpResponse.setStatus(401);
                return;
            }
        } else {
            chain.doFilter(request, response);
        }

        //chain.doFilter(request, response);

    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxMark = request.getHeader("X-Requested-With");
        if (IS_DEBUG) {
            logger.debug("request.getHeader(\"X-Requested-With\") = " + ajaxMark);
        }
        return (ajaxMark != null && ajaxMark.equals("XMLHttpRequest"));
    }

    /**
     * 將SSOOutputVO放入Session內
     * @param httpRequest
     * @param httpResponse
     * @param ssoOutputVO
     * @param token
     */
    private void setSsoOutputVOInSessionByConfig(HttpServletRequest httpRequest, HttpServletResponse httpResponse, SSOOutputVO ssoOutputVO,
            String token, Map<String, String> switchUsers) {
        String sessionAttrName = ssoProperty.getSessionAttrName();
        String switchUserSessionAttrName = ssoProperty.getSwitchUserSessionAttrName();
        String requestAttrName = ssoProperty.getRequestAttrName();
        String checkKey = ssoProperty.getCheckKey();
        String aesSeed = ssoProperty.getAesSeed();

        // 在驗證完成時，重新再將 session 資訊及 ID 置換，避免取得前次未登出人員資訊
        HttpSession session = httpRequest.getSession();

        session.invalidate();

        session = httpRequest.getSession();

        String newID = UUID_PREFIX + UUID.randomUUID().toString();

        if (IS_DEBUG) {
            logger.debug("will set new INITSESSIONID :" + newID);
        }

        String sessionTokenKey = ssoProperty.getSessionTokenKey();
        String requestTokenKey = ssoProperty.getRequestTokenKey();

        // 仿 eBAF 的登入資訊
        Cookie initSessionIdCookie = new Cookie("INITSESSIONID", session.getId());
        initSessionIdCookie.setSecure(false);
        initSessionIdCookie.setPath("/");
        httpResponse.addCookie(initSessionIdCookie);

        httpRequest.setAttribute(sessionAttrName, ssoOutputVO);
        session.setAttribute(UserObjectUtil.SESSION_KEY, newID);
        session.setAttribute(sessionAttrName, ssoOutputVO);
        session.setAttribute(switchUserSessionAttrName, switchUsers);

        if (token != null) {
            AESHelper aesHelper = new AESHelper(aesSeed);
            String encryptKey = aesHelper.encryptString(checkKey);
            String encryptToken = aesHelper.encryptString(token);

            httpRequest.setAttribute(requestAttrName, encryptKey);
            httpRequest.setAttribute(requestTokenKey, encryptToken);
            session.setAttribute(sessionTokenKey, token);
        }
    }

    /**
     * 初始登入資訊
     * @param httpRequest
     * @param httpResponse
     * @param custAP_ID
     * @param token
     * @param isLoadMember
     * @return
     * @throws IOException
     */
    private boolean initalizeCubSsoAuthentication(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String custAP_ID,
            String token, boolean isLoadMember) throws IOException {

        String apId;
        if (custAP_ID == null) {
            apId = ssoProperty.getApId();
        } else {
            apId = custAP_ID;
        }

        //String serverURL = ssoPropertySSOUtils.getProperty(SSO_ATTR.SERVER_URL);
        String serverURL = ssoProperty.getServerUrl();
        //serverURL = "";
        String ip = httpRequest.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = httpRequest.getRemoteAddr();
        }

        SSOOutputVO ssoOutputVO;

        if (isLoadMember) {

            String empId = httpRequest.getParameter("STRESS_TESTING_EMP_ID");
            String empName = httpRequest.getParameter("STRESS_TESTING_EMP_NAME");
            String brancId = httpRequest.getParameter("STRESS_TESTING_BRANCH_ID");
            String branchName = httpRequest.getParameter("STRESS_TESTING_BRANCH_NAME");
            String role = httpRequest.getParameter("STRESS_TESTING_ROLE");

            if (IS_DEBUG) {
                logger.debug("=================== Parameter ==============");
                logger.debug("EmpId      => " + empId);
                logger.debug("Empname    => " + empName);
                logger.debug("Branchid   => " + brancId);
                logger.debug("Branchname => " + branchName);
                logger.debug("Role       => " + role);
                logger.debug("============================================");
            }

            if (IS_DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("EmpID=[").append(empId).append("], Token=[").append(token).append("]");
                logger.debug(sb.toString());
            }

            if (StringUtils.isBlank(empId) || StringUtils.isBlank(empName) || StringUtils.isBlank(brancId)
                    || StringUtils.isBlank(branchName) || StringUtils.isBlank(role)) {
                if (IS_DEBUG) {
                    logger.debug("壓測人員資訊不足");
                }
                return false;
            }

            ssoOutputVO = new SSOOutputVO();
            ssoOutputVO.setEmpId(empId);
            ssoOutputVO.setEmpname(empName);
            ssoOutputVO.setBranchid(brancId);
            ssoOutputVO.setBranchname(branchName);
            ssoOutputVO.setRole(role);

        } else {

            String userid = httpRequest.getParameter("UserID");

            // 組成DTO並且轉換成xml string
            SSOInputVO ssoInputVO = new SSOInputVO(apId, ip, token, userid);

            if (IS_DEBUG) {
                logger.debug(ssoInputVO.toString());
            }

            String xml = getReqXmlString(ssoInputVO);

            // write xml to sso server, then get response xml
            String respXml = doSsoValidation(serverURL, xml);

            // unmarshal response xml
            ssoOutputVO = parseRespXml(respXml);
            ssoOutputVO.setAPID(apId);

            if (ssoOutputVO == null || !"0".equals(ssoOutputVO.getReturncode())) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, respXml);
                if (IS_DEBUG) {
                    logger.debug("authentication failed.");
                }
                return false;
            }

        }

        if (IS_DEBUG) {
            logger.debug("force create new seesion when login ....");
        }

        setSsoOutputVOInSession(httpRequest, httpResponse, ssoOutputVO, token);

        if (IS_DEBUG) {
            logger.debug("authentication succeeded.");
        }

        return true;

    }

    /**
     * 
     * @param ssoInputVO
     * @return
     */
    private String getReqXmlString(SSOInputVO ssoInputVO) {

        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SSOInputVO.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(ssoInputVO, sw);
        } catch (JAXBException jaxbe) {
            logger.error("", jaxbe);
        }

        return sw.toString();

    }

    /**
     * 解析 SSO 驗證後回傳 XML 
     * @param respXml
     * @return
     */
    private SSOOutputVO parseRespXml(String respXml) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(SSOOutputVO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(respXml);
            return (SSOOutputVO) unmarshaller.unmarshal(reader);

        } catch (JAXBException e) {
            logger.error("取得  CubSsoOutputDto 異常，不視為錯誤", e);
            return null;
        }

    }

    /**
     * 驗證登入身份
     * @param serverUrl
     * @param reqXml
     * @return
     * @throws IOException
     */
    private String doSsoValidation(String serverUrl, String reqXml) throws IOException {

        String respXml = null;

        try {

            HttpURLConnection conn = getSSOServerConnection(serverUrl);

            DataOutputStream output = null;

            try {

                output = new DataOutputStream(conn.getOutputStream());
                output.writeBytes(reqXml);

            } finally {
                if (output != null) {
                    try {
                        output.flush();
                        output.close();
                        output = null;
                    } catch (Exception e) {
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            InputStream input = null;

            try {

                input = conn.getInputStream();

                byte[] ba = new byte[1024];

                while (input.read(ba) != -1) {
                    sb.append(new String(ba, CHARSET).trim());
                    ba = new byte[1024];
                }

            } finally {
                if (input != null) {
                    try {
                        input.close();
                        input = null;
                    } catch (Exception e) {
                    }
                }
            }

            respXml = sb.toString();

        } finally {
            if (IS_DEBUG) {
                logger.debug("===================================");
                logger.debug("request to SSO Server");
                logger.debug("server url    => " + serverUrl);
                logger.debug("request XML   => " + reqXml);
                logger.debug("response XML  => " + respXml);
                logger.debug("===================================");
            }

        }

        return respXml;
    }

    /**
     * 取得 SSO server 連線
     * @param serverUrl
     * @return
     * @throws IOException
     */
    private HttpURLConnection getSSOServerConnection(String serverUrl) throws IOException {

        if (serverUrl == null) {
            throw new IllegalArgumentException("未設定 SSO Server URL");
        }

        // 設定 SSL Context
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
        } };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            logger.error("", e);
        }

        URL url = new URL(serverUrl);

        HttpURLConnection conn;
        if (serverUrl.length() > 5 && "https".equalsIgnoreCase(serverUrl.substring(0, 5))) {
            conn = (HttpURLConnection) ((HttpsURLConnection) url.openConnection());
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }

        conn.setRequestMethod("POST");
        conn.setInstanceFollowRedirects(true);
        conn.setUseCaches(false);
        conn.setDefaultUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + CHARSET);
        return conn;
    }

    /**
     * 將SSOOutputVO放入Session內
     * @param httpRequest
     * @param httpResponse
     * @param ssoOutputVO
     * @param token
     */
    private void setSsoOutputVOInSession(HttpServletRequest httpRequest, HttpServletResponse httpResponse, SSOOutputVO ssoOutputVO,
            String token) {
        String sessionAttrName = ssoProperty.getSessionAttrName();
        String requestAttrName = ssoProperty.getRequestAttrName();
        String checkKey = ssoProperty.getCheckKey();
        String aesSeed = ssoProperty.getAesSeed();

        // 在驗證完成時，重新再將 session 資訊及 ID 置換，避免取得前次未登出人員資訊
        HttpSession session = httpRequest.getSession();

        session.invalidate();

        session = httpRequest.getSession();

        String newID = UUID_PREFIX + UUID.randomUUID().toString();

        if (IS_DEBUG) {
            logger.debug("will set new INITSESSIONID :" + newID);
        }

        String sessionTokenKey = ssoProperty.getSessionTokenKey();
        String requestTokenKey = ssoProperty.getRequestTokenKey();

        // 仿 eBAF 的登入資訊
        Cookie initSessionIdCookie = new Cookie(INIT_SESSION_ID_COOKIE_NAME, newID);
        initSessionIdCookie.setSecure(false);
        initSessionIdCookie.setPath("/");
        httpResponse.addCookie(initSessionIdCookie);

        httpRequest.setAttribute(sessionAttrName, ssoOutputVO);
        session.setAttribute(UserObjectUtil.SESSION_KEY, newID);
        session.setAttribute(sessionAttrName, ssoOutputVO);

        if (token != null) {
            AESHelper aesHelper = new AESHelper(aesSeed);
            String encryptKey = aesHelper.encryptString(checkKey);
            String encryptToken = aesHelper.encryptString(token);

            httpRequest.setAttribute(requestAttrName, encryptKey);
            httpRequest.setAttribute(requestTokenKey, encryptToken);
            session.setAttribute(sessionTokenKey, token);
        }
    }

    /**
     * 當 SSO 驗證成功時，指定 header 關鍵字，使 eBAF 判斷為登入而不重新產生新的 session id
     * @author NT46589
     *
     */
    private class SSOFilterLoginSuccessServletRequest extends HttpServletRequestWrapper {

        public SSOFilterLoginSuccessServletRequest(HttpServletRequest request) {
            super(request);

        }

        public String getHeader(String name) {

            if ("SSO-Token".equals(name) || IS_FISRT_ENTER.equals(name)) {
                return "Y";
            }

            return super.getHeader(name);
        }
    }
}
