package com.hacredition.xph.hacredition.mvp.entity;

/**
 * Created by pc on 2017/3/8.
 */

public class BaseAdapterItem implements Comparable<BaseAdapterItem>{

    private Integer sortId;

    private String name;

    private String value;

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(BaseAdapterItem baseAdapterItem) {
        return this.getSortId().compareTo(baseAdapterItem.getSortId());
    }
}
