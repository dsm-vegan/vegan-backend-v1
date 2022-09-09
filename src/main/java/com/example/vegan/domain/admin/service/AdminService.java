package com.example.vegan.domain.admin.service;

import com.example.vegan.domain.report.entity.PostReport;
import com.example.vegan.domain.report.repository.PostReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final PostReportRepository postReportRepository;
    @Transactional
    public List<PostReport> reportList(){
        return postReportRepository.findAll();
    }
}
