package com.example.facebook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String caption;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer likes;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> likedUsers = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REFRESH,orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-mm-dd нн:mm:ss")
    private LocalDateTime createDate;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    public Post() {

    }
}


