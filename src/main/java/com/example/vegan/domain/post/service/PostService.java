package com.example.vegan.domain.post.service;

import com.example.vegan.domain.post.controller.dto.PostRequest;
import com.example.vegan.domain.post.entity.Image;
import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.post.repository.ImageRepository;
import com.example.vegan.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ImageRepository imageRepository;
    private final PostRepository postRepository;

    @Transactional
    public Post create(PostRequest request){
        return postRepository.save(Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build());
    }

    @Transactional
    public Post update(PostRequest request, Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        post.update(request.getTitle(), request.getContent());
        return post;
    }

    @Transactional
    public void delete(Long id){
        postRepository.deleteById(id);
    }

    @Transactional
    public Post detail(Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Transactional
    public List<Post> search(String keyword){
        return postRepository.findByTitleContaining(keyword);
    }

    @Transactional
    public String uploadImage(MultipartFile image) {
        UUID uuid = UUID.randomUUID();
        String imageName = uuid + "-" + image.getOriginalFilename();

        Image saveImage = new Image(imageName);

        imageRepository.save(saveImage);
        return imageName;
    }
}
