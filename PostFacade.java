package com.example.facebook.facade;

import com.example.facebook.dto.PostDTO;
import com.example.facebook.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostFacade {
    public PostDTO postToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setUsername(post.getUser().getUsername());
        postDTO.setCaption(post.getCaption());
        postDTO.setTitle(post.getTitle());
        postDTO.setLikes(post.getLikes());
        postDTO.getLikedUsers(post.getLikedUsers());
        postDTO.getLocation(post.getLocation());

        return postDTO;
    }
}
