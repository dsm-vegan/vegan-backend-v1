package com.example.vegan.domain.report.service;

import com.example.vegan.domain.post.entity.Post;
import com.example.vegan.domain.post.repository.PostRepository;
import com.example.vegan.domain.report.entity.PostReport;
import com.example.vegan.domain.report.repository.PostReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final PostReportRepository postReportRepository;
    private final PostRepository postRepository;

    @Transactional
    public void report(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        PostReport postReport = new PostReport(post);
        postReportRepository.save(postReport);
    }
}
