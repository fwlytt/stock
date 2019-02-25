package com.stock.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static final char UNDERLINE='_';

    /**驼峰转下划线**/
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**根据年月获取第一天和最后一天**/
    public static Map<String, String> getDateByMonth(String param) {
        Map<String, String> map = new HashMap<>();
        String[] strings = param.split("-");
        int year = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

        //获取该月第一天
        Calendar cal= Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        map.put("startTime", sm.format(cal.getTime()));

        //获取该月最后一天
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        map.put("endTime", sm.format(cal.getTime()));
        return map;
    }
}