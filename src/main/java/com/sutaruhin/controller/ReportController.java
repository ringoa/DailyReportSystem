package com.sutaruhin.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sutaruhin.entity.Employee;
import com.sutaruhin.entity.Report;
import com.sutaruhin.service.ReportService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	ReportService reportService;

	@GetMapping("/allList")
	public String getAllLists(Principal principal, Model model) {
		model.addAttribute("reports", reportService.getAllReports());
		List<Report> myReports = reportService.getReports(principal.getName());
		Report firstReport = myReports.get(0);
		Integer employeeId = firstReport.getEmployee().getId();

		model.addAttribute("employeeId", employeeId);

		return "report/allList";
	}

	@GetMapping("/list")
	public String getMyList(Principal principal, Model model) {
		List<Report> reports = reportService.getReports(principal.getName());
		Report firstReport = reports.get(0);
		Integer employeeId = firstReport.getEmployee().getId();
		model.addAttribute("reports", reports);
		model.addAttribute("employeeId", employeeId);

		return "report/list";
	}

	@PostMapping("/details")
	public String postReportDetails(@RequestParam("id") Integer reportId, Model model) {
		Report report = reportService.getReportDetails(reportId);
		model.addAttribute("report", report);

		return "report/detail";
	}

	@GetMapping("/register")
	public String getRegister(Report report, @RequestParam("id") Integer employeeId, Model model) {
		Employee employee = reportService.getEmployee(employeeId);
		report.setEmployee(employee);
		model.addAttribute("report", report);

		return "report/register";
	}

	@PostMapping("/register")
	public String postRegister(@Valid @ModelAttribute Report report, BindingResult result) {
		if(result.hasErrors()) {
			return "report/register";
		}
		reportService.saveReport(report);

		return "redirect:/report/allList";
	}

	@GetMapping("/update")
	public String getUpdate(@RequestParam("id") Integer reportId, Model model) {
		Report report = reportService.getReportDetails(reportId);
		model.addAttribute("report", report);

		return "report/update";
	}

	@PostMapping("/update")
	public String postUpdate(@Valid @ModelAttribute Report report, BindingResult result) {
		if(result.hasErrors()) {
			return "report/update";
		}
		reportService.updateReport(report);

		return "redirect:/report/allList";
	}

}
