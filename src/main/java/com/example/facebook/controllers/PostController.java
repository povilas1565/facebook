package com.example.facebook.controllers;

import com.example.facebook.dto.PostDTO;
import com.example.facebook.entity.Post;
import com.example.facebook.facade.PostFacade;
import com.example.facebook.payload.response.MessageResponse;
import com.example.facebook.service.PostService;
import com.example.facebook.validators.ResponseErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostFacade postFacade;

    @Autowired
    private PostService postService;

    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> listErrors = responseErrorValidator.mappedValidatorService(bindingResult);

        Post post = postService.createPost(postDTO, principal);
        PostDTO postCreated = postFacade.postToPostDTO(post);

        return new ResponseEntity<>(postCreated, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> postDTOList = postService.getAllPosts()
                .stream()
                .map(postFacade::postToPostDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/posts")
    public ResponseEntity<List<PostDTO>> getAllPostsForUser(Principal principal) {
        List<PostDTO> postDTOList = postService.getAllPostsForUser(principal)
                .stream()
                .map(postFacade::postToPostDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(postDTOList, HttpStatus.OK);
    }

    @PostMapping("/{postId}/{username}/like")
    public ResponseEntity<PostDTO> likePost(@PathVariable("postId") String postId,
                                            @PathVariable("username") String username) {
        Post post = postService.setLikeToPost(Long.parseLong(postId), username);
        PostDTO postDTO = postFacade.postToPostDTO(post);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PostMapping("/{postId}/delete")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable("postId") String postId, Principal principal) {
        postService.deletePost(Long.parseLong(postId), principal);
        return new ResponseEntity<>(new MessageResponse("The post" + postId + "were deleted"), HttpStatus.OK);
    }
}




