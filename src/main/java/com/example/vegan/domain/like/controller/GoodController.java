package com.example.vegan.domain.like.controller;

import com.example.vegan.domain.like.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GoodController {

    private final GoodService likeService;

    @PostMapping("/board/like/{id}")
    public void like(@PathVariable Long id){
        likeService.insertLike(id);
    }

    @DeleteMapping("/board/like/{id}")
    public void unlike(@PathVariable Long id){
        likeService.deleteLike(id);
    }
}
