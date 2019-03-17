package com.gdl.schedule.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtil {
    /**
     * 添加cookie
     *
     * @param response
     * @param name
     * @param value     默认以'utf-8'编码的值
     * @param maxAge    maxAge>0: cookie会保存在硬盘上，超过指定时间则删除;
     *                   <0: 设置成默认值(存放到内存),浏览器关闭则被删除;=0: 立即删除
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) throws UnsupportedEncodingException {
        value= URLEncoder.encode(value,"UTF-8");//对cookie的value进行url编码
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     *
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie uid = new Cookie(name, null);
        uid.setPath("/");
        uid.setMaxAge(0);
        response.addCookie(uid);
    }

    /**
     * 获取cookie值
     *
     * @param request
     * @return 'utf-8'格式的cookie值，若为空返回null
     */
    public static String getUid(HttpServletRequest request, String cookieName) throws UnsupportedEncodingException {
        Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return URLDecoder.decode(cookie.getValue(),"UTF-8"); //默认使用utf-8进行解码
                //return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (GeneralUtil.isEmpty(cookies)) {
            return null;
        }
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        return cookie;
    }

}
