package com.example.vegan.domain.report.controller;

import com.example.vegan.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/board/report/{id}")
    public void report(@PathVariable Long id){
        reportService.report(id);
    }
}
