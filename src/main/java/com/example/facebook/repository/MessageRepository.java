package com.example.facebook.repository;

import com.example.facebook.entity.Message;
import com.example.facebook.entity.User;
import com.example.facebook.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByUserId(Long userId);

    Optional<Message> findByMessageId(Long postId);
}

