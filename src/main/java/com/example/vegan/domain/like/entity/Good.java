package com.example.vegan.domain.like.entity;

import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GOOD")
@Builder
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"user"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Post post;

    @JsonIgnoreProperties(value = {"posts", "comments","goods"})
    @ManyToOne
    @JoinColumn(name = "post_id")
    private User user;

    public Good(Post post, User user){
        this.post = post;
        this.user = user;
    }
}
