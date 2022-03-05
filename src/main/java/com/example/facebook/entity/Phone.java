package com.example.facebook.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column (nullable = false)
    private String username;

    @Column (name = "userid")
    private Long userId;

    @Column(nullable = true)
    private String location;

    @Column (nullable = false)
    private String title;


    @Column (nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd нн:mm:ss")
    private LocalDateTime createDate;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    public Object getMessage() {
        return null;
    }
}




