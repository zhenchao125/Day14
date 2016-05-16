package com.qianfeng.day14_contacts;

/**
 * Created by Administrator on 2016/5/12.
 */
public class User{
    private String name;
    private String phone;
    private String eMail;

    public User(){
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String geteMail(){
        return eMail;
    }

    public void seteMail(String eMail){
        this.eMail = eMail;
    }

    @Override
    public String toString(){
        return "User{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
