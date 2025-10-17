package com.sutaruhin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sutaruhin.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{

	List<Report> findByEmployee_Id(int employeeId);

	Report findReportById(Integer id);

}