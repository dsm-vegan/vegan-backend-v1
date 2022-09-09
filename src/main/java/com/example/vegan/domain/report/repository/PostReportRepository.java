package com.example.vegan.domain.report.repository;

import com.example.vegan.domain.report.entity.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReportRepository extends JpaRepository<PostReport, Long> {
}
