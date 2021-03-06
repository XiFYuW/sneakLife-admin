package com.sneaklife.config;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/12/22 20:40
 */
public enum SneakLifeSystemEnum {

    SNEAK_LIFE_CACHE_DIR_CUT(":"),

    SNEAK_LIFE_NAME("SNEAK_LIFE_NAME"),

    OPERA_COLUMNS("opera_co"),

    OPERA_COLUMNS_VALUE("功能字段"),

    OPERA_IN("opera_in"),

    OPERA_IN_VALUE("功能输入"),

    OPERA_SB("opera_sb"),

    OPERA_SB_VALUE("功能操作"),

    OPERA_BO("opera_bo"),

    OPERA_BO_VALUE("功能查询");

    private final String name;

    SneakLifeSystemEnum(String name) {
        this.name = name;
    }

    public String toName(){
        return this.name;
    }
}
