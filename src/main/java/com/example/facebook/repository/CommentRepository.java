package com.example.facebook.repository;
import com.example.facebook.entity.Comment;
import com.example.facebook.entity.Post;
import com.example.facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

    Comment findByIdAndUserId(Long postId, Long UserId);

}


