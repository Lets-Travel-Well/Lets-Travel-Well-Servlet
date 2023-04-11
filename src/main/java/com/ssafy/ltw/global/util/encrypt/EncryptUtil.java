package com.ssafy.ltw.global.util.encrypt;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class EncryptUtil {
    private static EncryptUtil instance = new EncryptUtil();
    public static EncryptUtil getInstance(){
        return instance;
    }
    private static final int SALT_SIZE = 16;

    public String getSALT() throws Exception {
        SecureRandom rnd = new SecureRandom();
        byte[] temp = new byte[SALT_SIZE];
        rnd.nextBytes(temp);

        return Byte_to_String(temp);
    }

    public String Byte_to_String(byte[] temp) {
        StringBuilder sb = new StringBuilder();
        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }

    public String Hashing(byte[] password, String Salt) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");	// SHA-256 해시함수를 사용

        for(int i = 0; i < 10000; i++) {
            String temp = Byte_to_String(password) + Salt;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성
            md.update(temp.getBytes());						// temp 의 문자열을 해싱하여 md 에 저장해둔다
            password = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다
        }

        return Byte_to_String(password);
    }
}
