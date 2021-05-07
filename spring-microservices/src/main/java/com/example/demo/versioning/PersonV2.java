package com.example.demo.versioning;

public class PersonV2
{
    Name name;

    public PersonV2(Name name) {
        this.name = name;
    }

    public PersonV2() {
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonV2{" +
                "name=" + name +
                '}';
    }
}
