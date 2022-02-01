package me.tsaheylu.util;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignUtil {

  private static String secrectkey = "Vu5enH3tSK1wKR5mF+cSglOgqeuosG0B6EBK/6aBsAU=";

  public static String getSHACode(String data) {
    try {
      Mac mac = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(secrectkey.getBytes("utf-8"), "HmacSHA256");
      mac.init(secretKeySpec);
      byte[] digest = mac.doFinal(data.getBytes("utf-8"));
      String encodedData = byteArrayToHexString(digest).toUpperCase();

      return encodedData;
    } catch (InvalidKeyException e) {
      throw new RuntimeException("Invalid key exception while converting to HMac SHA256");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String byteArrayToHexString(byte[] b) {
    StringBuilder hs = new StringBuilder();
    String stmp;
    for (int n = 0; b != null && n < b.length; n++) {
      stmp = Integer.toHexString(b[n] & 0XFF);
      if (stmp.length() == 1) hs.append('0');
      hs.append(stmp);
    }
    return hs.toString().toLowerCase();
  }

  public static void main(String[] args)
      throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException,
          BadPaddingException, NoSuchAlgorithmException {
    /*    KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
    // 产生密钥
    SecretKey secretKey = keyGenerator.generateKey();
    // 获取密钥

    byte[] key = secretKey.getEncoded();
    System.out.println(Base64.getEncoder().encodeToString(key));*/
  }
}
