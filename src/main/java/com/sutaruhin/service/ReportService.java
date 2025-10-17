package com.sutaruhin.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sutaruhin.entity.Authentication;
import com.sutaruhin.entity.Employee;
import com.sutaruhin.entity.Report;
import com.sutaruhin.repository.AuthenticationRepository;
import com.sutaruhin.repository.EmployeeRepository;
import com.sutaruhin.repository.ReportRepository;

import jakarta.transaction.Transactional;

@Service
public class ReportService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Autowired
	private ReportRepository reportRepository;

	public List<Report> getAllReports(){

		return reportRepository.findAll();
	}

	public List<Report> getReports(String code){
		Authentication authentication = authenticationRepository.findByCode(code);
		Integer employeeId = authentication.getEmployee().getId();
		
		return reportRepository.findByEmployee_Id(employeeId);
	}

	public Report getReportDetails(Integer reportId) {

		return reportRepository.findReportById(reportId);
	}

	public Employee getEmployee(Integer employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("社員が存在しません"));

		return employee;
	}

	@Transactional
	public Report saveReport(Report report) {
		employeeRepository.save(report.getEmployee());
		report.setCreatedAt(LocalDateTime.now());
		report.setUpdatedAt(LocalDateTime.now());
		
		return reportRepository.save(report);
	}

	@Transactional
	public Report updateReport(Report report) {
		Employee employee = employeeRepository.findById(report.getEmployee().getId())
				.orElseThrow(() -> new RuntimeException("社員が存在しません"));

		report.setEmployee(employee);
		report.setUpdatedAt(LocalDateTime.now());

		return reportRepository.save(report);
	}
}
