package com.example.demo.user;

import com.sun.istack.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Schema(description =  "This is a social media user")
@Entity //maintained by JPA - class is an entity and is mapped to a database table.
public class User
{
    @Size(min = 2, message =  "name should have atleast 2 characters")
    @Schema(description = "Name should have atleast 2 characters")
    private String firstName;

    @Past
    @Schema(description = "Birthdate should be in the past")
    private Date birthDate;

    @Id
    @GeneratedValue
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
