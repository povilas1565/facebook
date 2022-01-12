package com.example.facebook.repository;
import com.example.facebook.entity.Video;
import com.example.facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    Optional<Video> findByUserId(Long userId);

    Optional<Video> findByVideoId(Long videoId);
}

