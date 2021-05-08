package com.example.demo.user;

import com.sun.istack.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/*
SQL for creation =
create table user (id integer not null, birth_date timestamp, first_name varchar(255), primary key (id))
 */


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

    @OneToMany(mappedBy = "user") //name of the field in the Post class that corresponds to User
    private List<Post> posts;

    public User() {
    }

    public User(String firstName, Date birthDate, Integer id, List<Post> posts) {
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.id = id;
        this.posts = posts;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", id=" + id +
                ", posts=" + posts +
                '}';
    }
}
