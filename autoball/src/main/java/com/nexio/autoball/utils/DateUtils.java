package com.nexio.autoball.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    public static String getIssue() {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyMMddHHmm");
        Date current = new Date();
        String fmt = sdFormat.format(current);
        return fmt;
    }
    public static String checkIssue(String drawPath,Date date) {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
        String fmt = sdFormat.format(date);
        return drawPath+"/Ball"+fmt+".txt";
    }


}
