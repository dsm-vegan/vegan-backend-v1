package com.example.vegan.domain.post.entity;

import com.example.vegan.domain.like.entity.Good;
import com.example.vegan.domain.report.entity.PostReport;
import com.example.vegan.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POST")
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @JsonIgnoreProperties({"posts"})
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Good> likes;

    @JsonIgnoreProperties(value = {"post","user"})
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostReport> postReports;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
