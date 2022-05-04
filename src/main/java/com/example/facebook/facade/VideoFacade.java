package com.example.facebook.facade;

import com.example.facebook.dto.VideoDTO;
import com.example.facebook.entity.Video;
import org.springframework.stereotype.Component;

@Component
public class VideoFacade {
    public VideoDTO videoToVideoDTO(Video video) {
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(video.getId());
        videoDTO.setLocation(video.getLocation());

        return videoDTO;
    }
}
