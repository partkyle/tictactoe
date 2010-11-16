package util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Util {
	public static String encrypt(String plaintext, Object... salts) {
		try {
			MessageDigest md = null;
			md = MessageDigest.getInstance("SHA");
			String salted = plaintext;
			for (Object salt : salts) {
				salted += salt;
			}
			md.update(salted.getBytes("UTF-8"));

			byte raw[] = md.digest();
			BigInteger number = new BigInteger(1, raw);
			return number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		}
		return null;
	}
}
