package com.example.demo.user;

import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController
{
    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> getAllUsers()
    {
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id)
    {
        User user = service.getUser(id);
//        returns a status code of 404 if the exception is thrown
        if (user == null)
        {
            throw new UserNotFoundException("id = " + id);
        }
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

//        HATEOAS -> hyperlink as the engine of application state
        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user)
    {
        User savedUser = service.saveUser(user);
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest() // get /users
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
//        to return the URI of the newly created resource.
        return ResponseEntity.created(location).build();
//        return HTTP Code CREATED
    }

    @DeleteMapping(path = "/users/{id}")
    public User deleteUser(@PathVariable int id)
    {
        User user = service.deleteUser(id);
        if (user != null)
        {
            return user;
        }
        else
        {
            throw new UserNotFoundException("userId = " + id);
        }
    }

    @GetMapping(path = "/users/{userId}/posts")
    public List<Post> getAllPostsForAUser(@PathVariable int userId)
    {
        List<Post> allPostsForUser = service.getAllPostsForUser(userId);
        if (allPostsForUser != null)
        {
            return allPostsForUser;
        }
        else
        {
            throw new UserNotFoundException("userId = " + userId);
        }
    }

    @PostMapping(path = "/users/{userId}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int userId, @Valid @RequestBody Post newPost)
    {
        Post savedPost = service.savePostForUser(userId, newPost);
        if (savedPost != null)
        {
            URI location =  ServletUriComponentsBuilder
                    .fromCurrentRequest() // get /users/{id}/posts
                    .path("/{userId}")
                    .buildAndExpand(savedPost.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        else
        {
            throw new UserNotFoundException("userId = " + userId);
        }
    }

    @GetMapping(path = "/users/{userId}/posts/{postId}")
    public Post getPostForUser(@PathVariable int userId, @PathVariable int postId)
    {
        Post post = service.getPost(userId, postId);
        if (post != null)
        {
            return post;
        }
        else
        {
            throw new ResourceNotFoundException("userId = " + userId + " , postId = " + postId);
        }
    }

}
