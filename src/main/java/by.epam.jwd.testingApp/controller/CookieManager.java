package by.epam.jwd.testingApp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

    private static CookieManager instance;

    private CookieManager(){}

    public static CookieManager getInstance() {
        CookieManager localInstance = instance;
        if (localInstance == null) {
            synchronized (CookieManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CookieManager();
                }
            }
        }
        return localInstance;
    }

    public Cookie[] removeCookie(HttpServletRequest request, String cookieName){
        if(request==null) return null;
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        if(cookieName==null) return cookies;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(cookieName)){
                cookie.setMaxAge(0);
                break;
            }
        }
        return cookies;
    }

    public String findValueByName(HttpServletRequest request, String cookieName){
        if(request==null) return null;
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookieName==null) return null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }


    public void rewriteCookie(HttpServletRequest request, HttpServletResponse response){
        if(request==null || response == null)return;
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return ;
        for(Cookie cookie:cookies){
            response.addCookie(cookie);
        }
    }

    public void addCookie(HttpServletResponse response, Cookie cookie){
        if(cookie==null || response == null)return;
        response.addCookie(cookie);
    }
}
