package com.laptrinhjavaweb.utils;

public class StringUtils {
    public static boolean isNullOrEmty(String str){
        if(str!=null && str!=""){
            return false;
        }
        return true;

    }
}
