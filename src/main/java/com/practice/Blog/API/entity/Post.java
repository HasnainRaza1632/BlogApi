package com.practice.Blog.API.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3)
    private String title;

    @Column(nullable = false)
    private String content;

    @CurrentTimestamp
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "post_author_id")
    private User author;

    @OneToMany(mappedBy = "post" , cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Comment> comments = new ArrayList<>();
}
