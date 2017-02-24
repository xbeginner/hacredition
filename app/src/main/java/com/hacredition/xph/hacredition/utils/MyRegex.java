package com.hacredition.xph.hacredition.utils;

/**
 * Created by pc on 2017/2/22.
 */

public  class MyRegex {

    public static final String NOTNULL = "/^\\s*$/";

    public static final String IDCARD = "/^(\\d{18,18}|\\d{15,15}|\\d{17,17}x)$/";

    public static final String ISFLOAT = "^\\\\d+(\\\\.\\\\d+)?$";

    public static final String EMAIL = "^[\\\\w-]+(\\\\.[\\\\w-]+)*@[\\\\w-]+(\\\\.[\\\\w-]+)+$";

    public static final String PHONENUMBER = "^((13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$";

    public static final String DATE = "^(\\\\d{4})-(\\\\d{1}|1[0-2])-(\\\\d{1}|[12]\\\\d{1}|3[01])$";

    public static final String TIME = "^(0\\\\d{1}|1\\\\d{1}|2[0-3]):[0-5]\\\\d{1}:([0-5]\\\\d{1})$";

}
