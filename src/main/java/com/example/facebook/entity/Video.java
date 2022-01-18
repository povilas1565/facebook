package com.example.facebook.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;


import javax.persistence.*;
import javax.sql.RowSet;
import java.time.LocalDateTime;

@Data
@Entity
public class Video {
    @Id
    private Long id;

    @Column(nullable = true)
    private String location;

    @Column(updatable = false)
    private String username;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] videoBytes;

    @JsonIgnore
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Comment comment;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd нн:mm:ss")
    private LocalDateTime createDate;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    public String getTitle() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}










