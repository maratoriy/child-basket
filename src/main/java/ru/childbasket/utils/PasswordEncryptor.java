package ru.childbasket.utils;

import lombok.experimental.UtilityClass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;

@UtilityClass
public class PasswordEncryptor {
    public static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");

        byte[] salt = new byte[16];

        sr.nextBytes(salt);

        return Arrays.toString(salt);
    }

    public static String getSecurePassword(String passwordToHash,
                                           String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(salt.getBytes());

        byte[] bytes = md.digest(passwordToHash.getBytes());

        StringBuilder sb = new StringBuilder();

        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                    .substring(1));
        }

        String generatedPassword = sb.toString();
        return generatedPassword;
    }
}
