package com.xpf.android.mock.entity;

/**
 * Created by x-sir on 2019-06-19 :)
 * Function:
 */
public class Xy0001Entity {

    /**
     * name : zhangsan
     * sex : 1
     * phone : 110
     */

    private String name;
    private String sex;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Xy0001Entity{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
