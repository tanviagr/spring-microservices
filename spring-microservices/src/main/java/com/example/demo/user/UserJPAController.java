package com.example.demo.user;

import com.example.demo.exception.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController
{
    @Autowired
    private UserRepository repository;

    @Operation(summary = "Get All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users Found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)),
//                    @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class))
                    })
    })
    @GetMapping(path = "jpa/users")
    public List<User> getAllUsers()
    {
        return repository.findAll();
    }

    @Operation(summary = "Get a User by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Found",
                    content = { @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    ) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    })
//                    content = @Content(mediaType = "application/json")
    })
    @GetMapping(path = "jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id)
    {
        Optional<User> user = repository.findById(id);
//        returns a status code of 404 if the exception is thrown
        if (!user.isPresent())
        {
            throw new UserNotFoundException("id = " + id);
        }
        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

//        HATEOAS -> hyperlink as the engine of application state
        return resource;
    }

    @DeleteMapping(path = "jpa/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        repository.deleteById(id);
    }

    @PostMapping(path = "jpa/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user)
    {
        User savedUser = repository.save(user);
        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest() // get /users
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
//        to return the URI of the newly created resource.
        return ResponseEntity.created(location).build();
//        return HTTP Code CREATED
    }

    @GetMapping(path = "jpa/users/{id}/posts")
    public List<Post> getAllPostsForUser(@PathVariable int id)
    {
        Optional<User> user = repository.findById(id);
        if (!user.isPresent())
        {
            throw new UserNotFoundException("id = " + id);
        }
        return user.get().getPosts();
    }

    /*
@GetMapping(path = "jpa/users/{userId}/posts")
    public List<Post> getAllPostsForAUser(@PathVariable int userId)
    {
        List<Post> allPostsForUser = repository.getAllPostsForUser(userId);
        if (allPostsForUser != null)
        {
            return allPostsForUser;
        }
        else
        {
            throw new UserNotFoundException("userId = " + userId);
        }
    }

    @PostMapping(path = "jpa/users/{userId}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int userId, @Valid @RequestBody Post newPost)
    {
        Post savedPost = repository.savePostForUser(userId, newPost);
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

    @GetMapping(path = "jpa/users/{userId}/posts/{postId}")
    public Post getPostForUser(@PathVariable int userId, @PathVariable int postId)
    {
        Post post = repository.getPost(userId, postId);
        if (post != null)
        {
            return post;
        }
        else
        {
            throw new ResourceNotFoundException("userId = " + userId + " , postId = " + postId);
        }
    }
    */
}
