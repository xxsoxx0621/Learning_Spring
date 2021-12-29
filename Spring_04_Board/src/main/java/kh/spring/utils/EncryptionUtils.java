package kh.spring.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptionUtils {
	public static String getSHA512(String str){
		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(str.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toReturn;
	}
}
