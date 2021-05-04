package com.example.demo.user;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

//should be marking as Repository but we are not making a database connection
@Component
public class UserDaoService
{
    private static List<User> userList = new ArrayList<>();
    private static Map<Integer, List<Post>> userToPostMap = new HashMap<>();

    private static int userCount;

    static {
        userCount = 3;
        userList.add(new User("tanvi", new Date(), 1));
        userList.add(new User("muskan", new Date(), 2));
        userList.add(new User("Rithija", new Date(), 3));

        userToPostMap.put(1, new ArrayList<>(
                Arrays.asList(
                        new Post(1, "This is a flower"),
                        new Post(2, "This is a second flower")
                )));
        userToPostMap.put(2, new ArrayList<>(
                Arrays.asList(
                        new Post(1, "I am the second user and I only like books")
                )
        ));
    }

    public List<User> findAll()
    {
        return userList;
    }

    public User saveUser(User user)
    {
        if (user.getId() == null)
        {
            user.setId(++userCount);
        }
        userList.add(user);
        return user;
    }

    public User getUser(int id)
    {
        for (User user : userList)
        {
            if (user.getId() == id)
            {
                return user;
            }
        }
        return null;
    }

    public User deleteUser(int id)
    {
        User user = userList
                .stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElse(null);
        userList.remove(user);
        if (user != null)
        {
            userToPostMap.remove(user.getId());
        }
        return user;
    }

    public List<Post> getAllPostsForUser(int userId)
    {
        if (getUser(userId) != null)
        {
            return userToPostMap.getOrDefault(userId, new ArrayList<>());
        }
        return null;
    }

    public Post savePostForUser(int userId, Post newPost)
    {
        if (getUser(userId) != null)
        {
            List<Post> userPosts = userToPostMap.getOrDefault(userId, new ArrayList<>());
            if (newPost.getId() == null)
            {
                newPost.setId(userPosts.size() + 1);
            }
            userPosts.add(newPost);
            userToPostMap.put(userId, userPosts);
            return newPost;
        }
        return null;
    }

    public Post getPost(int userId, int postId)
    {
        if (getUser(userId) != null)
        {
            List<Post> postListForUser = userToPostMap.get(userId);
            for (Post post : postListForUser)
            {
                if (post.getId() == postId)
                {
                    return post;
                }
            }
            return null;
        }
        return null;
    }
}
