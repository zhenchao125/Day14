package com.qianfeng.day14.db;

/**
 * Created by Administrator on 2016/5/12.
 */
public class User{

    private int _id;
    private String name;
    private int age;
    private String phone;

    public User(int _id, String name, int age, String phone){
        this(name, age, phone);
        this._id = _id;

    }

    public User(String name, int age, String phone){
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public User(){
    }

    public int get_id(){
        return _id;
    }

    public void set_id(int _id){
        this._id = _id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "User{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }


}
