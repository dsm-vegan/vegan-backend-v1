package com.example.vegan.domain.like.entity;

import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LIKE")
@Builder
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Like(Post post, User user){
        this.post = post;
        this.user = user;
    }
}
