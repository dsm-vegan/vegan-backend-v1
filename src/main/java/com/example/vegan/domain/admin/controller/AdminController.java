package com.example.vegan.domain.admin.controller;

import com.example.vegan.domain.admin.service.AdminService;
import com.example.vegan.domain.report.entity.PostReport;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin/report")
    public List<PostReport> reportList(){
        return adminService.reportList();
    }
}
