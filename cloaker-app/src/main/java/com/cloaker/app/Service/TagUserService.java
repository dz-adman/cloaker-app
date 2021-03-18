package com.cloaker.app.Service;

import okhttp3.Request;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class TagUserService {

    public Cookie setUserCookies(Request req, boolean isGoodBad) {
        //set user cookies to tag user as Good or Bad
        Cookie cookie = new Cookie("cloakerAppCookie",isGoodBad ? "Good" : "Bad");
        cookie.setMaxAge(1 * 24 * 60 * 60); // 1 day (24hrs)
        return cookie;
    }
}
