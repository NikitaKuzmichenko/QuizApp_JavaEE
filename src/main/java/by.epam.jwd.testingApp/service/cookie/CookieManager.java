package by.epam.jwd.testingApp.service.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

    private CookieManager(){}

    private static class SingletonHolder {
        public static final CookieManager HOLDER_INSTANCE = new CookieManager();
    }

    public static CookieManager getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
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
