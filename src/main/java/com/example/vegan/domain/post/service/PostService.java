package com.example.vegan.domain.post.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.vegan.domain.post.controller.dto.PostRequest;
import com.example.vegan.domain.post.entity.Image;
import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.post.repository.ImageRepository;
import com.example.vegan.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
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
    public void delete(Long postId, Long imageId, String key){
        postRepository.deleteById(postId);
        imageRepository.deleteById(imageId);
        amazonS3.deleteObject(bucket, key);
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
    public String upload(MultipartFile multipartFile) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        Image saveImage = new Image(s3FileName);

        imageRepository.save(saveImage);

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }
}
