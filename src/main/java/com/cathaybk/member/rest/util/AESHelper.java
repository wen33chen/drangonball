package com.cathaybk.member.rest.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cathay.common.util.Base64;

/**
 * AES 字串加解密的輔助工具，使用 AES128 位元的加密方式加密
 * 建立此工具時需同時傳入種子字串以產生加密用的金鑰字串。
 * 加解密之字串內皆以 UTF-8 來編解碼。加密完成的資料，以 Base64 編碼後回傳
 */
public class AESHelper {

    private Logger log = LoggerFactory.getLogger(getClass());

    /** 加密工具 */
    private Cipher cipher;

    /** 解密工具 */
    private Cipher deCipher;

    public AESHelper(String keySeed) {
        createKey(keySeed);
    }

    /**
     * 加密
     *@param source 欲被加密的字串
     *@return 加密後的 Base64 字串，失敗的話會回空字串
     */
    public String encryptString(String source) {

        String result = "";

        try {

            byte[] b = cipher.doFinal(source.getBytes("UTF-8"));
            result = Base64.encodeBytes(b, false);

        } catch (Exception e) {
            log.error("", e);
        }

        return result;

    }

    /**
     * 解密
     * @param source 加密的 Base64 字串
     * @return 解密後的字串，解密失敗回傳空字串
     */
    public String dencryptString(String source) {

        String result = "";

        try {

            byte[] b = Base64.decode(source);
            b = deCipher.doFinal(b);
            result = new String(b, "UTF-8");

        } catch (Exception e) {
            log.error("", e);
        }

        return result;
    }

    /**
     * 依傳進來的 keySeed 產生金鑰字串，並建立加解密工具組
     */
    private void createKey(String seed) {

        try {

            //default key string to use
            String keyStr = "1234567890123456";

            //用傳進來的 seed 字串產生金鑰字串，演算的邏輯同行銷資訊部的『行銷 EZGO』作法
            byte[] keyByte = new String(seed + "cathay" + seed + "Sug" + seed + "APP").getBytes("utf-8");
            String encodeKey = Base64.encodeBytes(keyByte, false);
            int subIndex = (int) seed.charAt(0);
            subIndex %= 4;
            seed = encodeKey.substring(0, subIndex) + seed + encodeKey.substring(subIndex + seed.length());
            keyByte = new String(seed).getBytes("utf-8");
            encodeKey = Base64.encodeBytes(keyByte, false);

            String result = "";
            for (int i = 0, j = encodeKey.length() / 2 - subIndex; i < 8; i++) { //長度限定16
                result += seed.charAt(i + subIndex / 2);
                result += encodeKey.charAt(j + i * 2);
            }

            keyStr = result;
            SecretKeySpec secKey = new SecretKeySpec(keyStr.getBytes("UTF-8"), "AES");

            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secKey);

            deCipher = Cipher.getInstance("AES");
            deCipher.init(Cipher.DECRYPT_MODE, secKey);

        } catch (Exception e) {
            log.error("", e);
        }

    }

}