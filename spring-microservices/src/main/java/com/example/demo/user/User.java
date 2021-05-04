package com.example.demo.user;

import com.sun.istack.Nullable;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class User
{
    @Size(min = 2, message =  "name should have atleast 2 characters")
    private String firstName;

    @Past
    private Date birthDate;

    private Integer id;

    public User(String firstName, Date birthDate, Integer id) {
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", id=" + id +
                '}';
    }
}
