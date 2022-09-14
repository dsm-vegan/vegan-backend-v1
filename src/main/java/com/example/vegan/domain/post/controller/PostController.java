package com.example.vegan.domain.post.controller;

import com.example.vegan.domain.post.controller.dto.DeleteRequest;
import com.example.vegan.domain.post.controller.dto.PostRequest;
import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/board")
    public Post create(@RequestBody PostRequest request){
        return postService.create(request);
    }

    @PatchMapping("/board/{id}")
    public Post update(@RequestBody PostRequest request, @PathVariable Long id){
        return postService.update(request, id);
    }

    @DeleteMapping("/board")
    public void delete(@RequestBody DeleteRequest request){
        postService.delete(request.getPostId(), request.getImageId(), request.getKey());
    }

    @GetMapping("/board/{id}")
    public Post detail(@PathVariable Long id){
        return postService.detail(id);
    }

    @GetMapping("/board")
    public List<Post> search(@RequestParam String keyword){
        return postService.search(keyword);
    }

    @PostMapping("/upload")
    public String upload(MultipartFile multipartFile) throws Exception {
        return postService.upload(multipartFile);
    }
}
