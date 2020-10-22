package com.nexio.autoball.utils;

import java.util.Map;
import java.util.TreeMap;

public class BallUtils {

    public static Map<String,String> parse(String drawResult){
        Map<String,String> balls = new TreeMap<>();
        String[] underline = drawResult.split("_");
        String[] ballIndex = underline[0].split(",");
        String[] ballIndexNums = underline[1].split(";");

        for(int i=0 ;i< ballIndex.length;i++){
            String index = ballIndex[i];
            String num = ballIndexNums[i];
            balls.put(index,num);
        }

        return balls;
    }

    public static void main(String[] arg) {
        Map<String,String> map = parse("6_10");
        Map<String,String> map1 = parse("1,2,3,4,5_0;9;8;7;6");

        Map<String,String> map2 = null;

        map.putAll(map1);

        map.putAll(map2);
        System.out.println(map);
    }

}
