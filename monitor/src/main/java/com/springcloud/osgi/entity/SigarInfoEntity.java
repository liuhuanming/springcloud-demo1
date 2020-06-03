package com.springcloud.osgi.entity;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-03 07:47
 */
public class SigarInfoEntity {
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数值
     */
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SigarInfoEntity(String name, String value) {
        super();
        this.name = name;
        this.value = value;

    }

    public SigarInfoEntity() {

    }
}
