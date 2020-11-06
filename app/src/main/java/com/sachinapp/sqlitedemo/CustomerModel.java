package com.sachinapp.sqlitedemo;

public class CustomerModel {

    private int id;
    private String name;
    private int age;
    private boolean isActive;

    CustomerModel(){}

    CustomerModel(int id, String name, int age, boolean isActive)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public boolean getActive()
    {
        return isActive;
    }

    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }
}
