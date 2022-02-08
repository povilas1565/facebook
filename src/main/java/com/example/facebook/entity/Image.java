package com.example.facebook.entity;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Data
@Entity
public class Image {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post posts;

    @Column(updatable = false)
    private String name;

    @Lob
    @Column (columnDefinition = "LONGBLOB")
    private byte[] imageBytes;

    @JsonIgnore
    private Long userId;

    @JsonIgnore
    private Long postId;

}


