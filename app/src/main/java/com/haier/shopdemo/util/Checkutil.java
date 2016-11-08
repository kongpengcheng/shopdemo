package com.haier.shopdemo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by centling on 2016/9/28.
 */
public class Checkutil {
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        CharSequence inputStr = phoneNumber;
        //正则表达式
        String phone="^1[34578]\\d{9}$" ;
        Pattern pattern = Pattern.compile(phone);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
