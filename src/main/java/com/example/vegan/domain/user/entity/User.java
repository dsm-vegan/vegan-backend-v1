package com.example.vegan.domain.user.entity;

import com.example.vegan.domain.like.entity.Good;
import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.report.entity.PostReport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "USER")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String introduction;

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Good> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<PostReport> postReports;

    @Column
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User update(String name){
        this.nickname = name;
        return this;
    }

    public void update(String nickname, String introduction){
        this.nickname = nickname;
        this.introduction = introduction;
    }

    public String getRoleKey(){
        return this.authority.name();
    }
}
