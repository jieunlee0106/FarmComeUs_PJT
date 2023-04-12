package com.ssafy.farmcu.utils;

import org.springframework.util.Base64Utils;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CookieUtil {

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0){
            for (Cookie cookie: cookies){
                if (name.equals(cookie.getName())) return Optional.of(cookie);
            }
        }
        return Optional.empty();
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        // 쿠키에 담아 반환
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name){
        Cookie[] cookies = request.getCookies();

//        TODO: cookie의 방식 공부
        if (cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (name.equals(cookie.getName())) {
                    cookie.setValue("name");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static String serialize(Object obj){
        return Base64Utils.encodeToUrlSafeString(SerializationUtils.serialize(obj));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64Utils.decodeFromUrlSafeString(cookie.getValue())
                )
        );
    }

}
