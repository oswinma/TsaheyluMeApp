package me.tsaheylu.util;

import me.tsaheylu.common.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static String getMailBody(InputStream resource) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(resource, "utf8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String getRandomString() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < Constants.CODE_LENGTH; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static Cookie getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie temp = cookies[i];
                if (temp.getName().equals(name)) {
                    return temp;
                }
            }
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie temp = cookies[i];
                if (temp.getName().equals(name)) {
                    return temp.getValue();
                }
            }
        }
        return null;
    }

    public static Cookie deleteCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie temp = cookies[i];
                if (temp.getName().equals(name)) {
                    temp.setMaxAge(0);
                    temp.setPath("/");
                    return temp;
                }
            }
        }

        return null;
    }

    public static String getHostByUrl(String url) {

        String[] s = url.split("/");
        return s[2];
    }

    public static boolean checkEmailFormat(String email) {
        Pattern p = Pattern.compile(Constants.EMAILADDPATTERN);
        Matcher m = p.matcher(email);
        return m.find();
    }
}
