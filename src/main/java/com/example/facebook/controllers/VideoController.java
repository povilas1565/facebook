package com.example.facebook.controllers;

import com.example.facebook.entity.Video;
import com.example.facebook.facade.VideoFacade;
import com.example.facebook.payload.response.MessageResponse;
import com.example.facebook.service.VideoService;
import com.example.facebook.validators.ResponseErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/video")
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoFacade videoFacade;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ResponseErrorValidator responseErrorValidator;


    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadVideoToProfile(@RequestParam("file") MultipartFile file,
                                                                Principal principal) throws IOException {
        videoService.uploadVideoToProfile(file, principal);
        return ResponseEntity.ok(new MessageResponse("Video upload successfully"));
    }
    @PostMapping("/{postId}/upload")
    public ResponseEntity<MessageResponse> uploadVideoToPost(@PathVariable("postId") String postId,
                                                             @RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {
        videoService.uploadVideoToPost(file, principal, Long.parseLong(postId));
        return ResponseEntity.ok(new MessageResponse("Video uploaded successfully to post" + postId));
    }
    @GetMapping("/profileVideo")
    public ResponseEntity<Video> getUserProfileVideo(Principal principal) {
        Video profileVideo = videoService.getUserProfileVideo(principal);
        return new ResponseEntity<>(profileVideo, HttpStatus.OK);
    }
    @GetMapping("/{postId}/video")
    public ResponseEntity<Video> getPostVideo(@PathVariable String postId){
        Video postVideo = videoService.getPostVideo(Long.parseLong(postId));
        return new ResponseEntity<>(postVideo, HttpStatus.OK);
    }
}
