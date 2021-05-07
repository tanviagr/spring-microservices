package com.example.demo.versioning;

public class PersonV1
{
    String name;
//    firstName and lastName being returned as Name in the first version of the API

    public PersonV1(String name) {
        this.name = name;
    }

    public PersonV1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "name='" + name + '\'' +
                '}';
    }
}
