package com.zcb.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    public static Boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
        if (verifyCodeActual == null || !verifyCodeActual.toLowerCase().equals(verifyCodeExpected.toLowerCase())) {
            return false;
        } else {
            return true;
        }
    }
}
