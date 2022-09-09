package com.example.vegan.domain.user.entity;

import com.example.vegan.domain.like.entity.Like;
import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.report.entity.PostReport;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Like> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<PostReport> postReports;

    @Column
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User update(String name){
        this.nickname = name;
        return this;
    }

    public String getRoleKey(){
        return this.authority.name();
    }
}
