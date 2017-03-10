package com.hacredition.xph.hacredition.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2017/3/8.
 */

public  class MyHashMaps {

    public static final Map<String,String> HOUSEHOLDBASICINFOMAP = new HashMap<String,String>(){
        {
            put("sfzno","身份证号");
            put("xingming","姓名");
            put("chushengriqi","出生日期");
            put("congyenianxian","从业年限");
            put("fubingyi","服兵役");
            put("hunyinzhuangkuang","婚姻状况");
        }
    };

    public static final Map<String,Integer> HOUSEHOLDBASICSORTMAP = new HashMap<String,Integer>(){
        {
            put("sfzno",0);
            put("xingming",1);
            put("chushengriqi",2);
            put("congyenianxian",3);
            put("fubingyi",4);
            put("hunyinzhuangkuang",5);
        }
    };


    public static final Map<String,Integer> HOUSEHOLDQUERYTYPES = new HashMap<String,Integer>(){
        {
            put("贷款情况",1);
            put("担保情况",2);
            put("保险情况",3);
            put("房产情况",4);
        }
    };

}
