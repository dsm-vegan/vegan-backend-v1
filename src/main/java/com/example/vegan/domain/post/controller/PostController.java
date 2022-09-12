package com.example.vegan.domain.post.controller;

import com.example.vegan.domain.post.controller.dto.PostRequest;
import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.post.service.PostService;
import com.example.vegan.domain.post.service.S3Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final S3Upload s3Upload;

    @PostMapping("/board")
    public Post create(@RequestBody PostRequest request){
        return postService.create(request);
    }

    @PatchMapping("/board/{id}")
    public Post update(@RequestBody PostRequest request, @PathVariable Long id){
        return postService.update(request, id);
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }

    @GetMapping("/board/{id}")
    public Post detail(@PathVariable Long id){
        return postService.detail(id);
    }

    @GetMapping("/board")
    public List<Post> search(@RequestParam String keyword){
        return postService.search(keyword);
    }

    @PostMapping("/image")
    public String upload(MultipartFile multipartFile) throws Exception {
        return s3Upload.upload(multipartFile);
    }
}
