package com.nexio.autoball.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class CryptoUtils {

    private static final Logger LOGGER = LogManager.getLogger(CryptoUtils.class);

    private static final String AES_CBC_PKCS5Padding = "AES/CBC/PKCS5Padding";

    public static byte[] aesEncrypt(SecretKey secretKey, byte[] iv, String msg) throws Exception{
        Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5Padding);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] byteCipherText = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        LOGGER.debug("Encrypted Text:" + Base64.getEncoder().encodeToString(byteCipherText));
        return byteCipherText;
    }

    public static String aesDecrypt(SecretKey secretKey, byte[] iv, byte[] cipherText) throws Exception{
        Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5Padding);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] decryptedText = cipher.doFinal(cipherText);
        String strDecryptedText = new String(decryptedText);
        LOGGER.debug("Decrypted Text:" + strDecryptedText);

        return strDecryptedText;
    }

    public static SecretKeySpec generateAESKeySpec(String key) {

        byte[] keyBytes = key.getBytes();
        if (keyBytes.length <= 16) {
            return new SecretKeySpec(Arrays.copyOf(keyBytes, 16), "AES");
        } else if (keyBytes.length <= 24) {
            return new SecretKeySpec(Arrays.copyOf(keyBytes, 24), "AES");
        } else {
            return new SecretKeySpec(Arrays.copyOf(keyBytes, 32), "AES");
        }
    }
}
