package com.umeca.infrastructure.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

public class SecureString {
    public static String encrypt(String key, String value)
            throws GeneralSecurityException {

        byte[] raw = key.getBytes(Charset.forName("US-ASCII"));
        if (raw.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,
                new IvParameterSpec(new byte[16]));

        return new BASE64Encoder().encode(cipher.doFinal(value.getBytes(Charset.forName("US-ASCII")))).replaceAll("(?:\\r\\n|\\n\\r|\\n|\\r)", "");
    }

    public static String decrypt(String key, String encrypted)
            throws GeneralSecurityException, IOException {

        byte[] raw = key.getBytes(Charset.forName("US-ASCII"));
        if (raw.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec,
                new IvParameterSpec(new byte[16]));

        byte[] original = cipher.doFinal(new BASE64Decoder().decodeBuffer(encrypted));

        return new String(original, Charset.forName("US-ASCII"));
    }
}