package com.example.facebook.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Video {
    @Id
    private Long id;

    @Column(updatable = false)
    private String name;

    @Lob
    @Column (columnDefinition = "LONGBLOB")
    private byte[] videoBytes;

    @JsonIgnore
    private Long userId;

    @JsonIgnore
    private Long postId;

    @Column (nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd нн:mm:ss")
    private LocalDateTime createDate;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }
}

