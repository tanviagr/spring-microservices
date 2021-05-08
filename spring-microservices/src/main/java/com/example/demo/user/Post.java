package com.example.demo.user;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.Size;

/*
SQL for creation =
create table post (id integer not null, description varchar(255), user_id integer, primary key (id))
alter table post add constraint FK72mt33dhhs48hf9gcqrq4fxte foreign key (user_id) references user
 */

/*
it's a good practice to mark many-to-one side as the owning side.
In other words, Post would be the owning side and User the inverse side.
By including the mappedBy attribute in the User class, we mark it as the inverse side.
At the same time, we also annotate the Post.User field with @ManyToOne, making Post the owning side.
This means that Post's reference is more important.
For ex :
Post post1 = new Post(userid = 1) //mapped to user 1
Post post2 = new Post(userid = 2) //mapped to user 2
User user1 = new User(Arrays.asList(post1, post2)) //mapped post2 to user 1.
This is inconsistent and JPA resolves this by looking at owning side - Post.
So post2 will still get allocated to user 2.
 */

@Schema(description = "This is the post that the user posts on social media")
@Entity
public class Post
{
    @Id
    @GeneratedValue
    Integer id;

    @Schema(description = "Every post has a description")
    @Size(min = 5, message = "post description should have atleast 5 characters")
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
