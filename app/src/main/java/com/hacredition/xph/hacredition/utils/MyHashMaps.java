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

}
