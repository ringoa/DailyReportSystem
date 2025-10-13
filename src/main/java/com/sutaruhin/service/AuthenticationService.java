package com.sutaruhin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sutaruhin.entity.Authentication;
import com.sutaruhin.entity.Employee;
import com.sutaruhin.repository.AuthenticationRepository;
import com.sutaruhin.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthenticationService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Authentication> getAuthentications(){
		return authenticationRepository.findByEmployeeDeleteFlag(0);
	}

	public Authentication getAuthenticationById(Integer id) {
	    return authenticationRepository.findByEmployee_Id(id);
	}

	@Transactional
	public Employee saveAuthentication(Authentication authentication) {

		if(authenticationRepository.existsByCode(authentication.getCode())) {
			throw new IllegalArgumentException("この社員番号はすでに使用されています");
		}

		Employee employee = authentication.getEmployee();
		employee.setCreatedAt(LocalDateTime.now());
		employee.setUpdatedAt(LocalDateTime.now());
		employee.setDeleteFlag(0);
		employeeRepository.save(employee);

		authentication.setEmployee(employee);
		authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
		authentication.setValidDate(LocalDate.parse("9999-12-31"));
		authenticationRepository.save(authentication);

		return employee;
	}

	@Transactional
	public Authentication updateAuthenticaion(Authentication authentication) {
		int id = authentication.getEmployee().getId();
		Authentication existingAuth = authenticationRepository.findByEmployee_Id(id);
		String exisitingCode = existingAuth.getCode();

		if(authenticationRepository.existsByCode(authentication.getCode()) && !exisitingCode.equals(authentication.getCode())) {
			throw new IllegalArgumentException("この社員番号はすでに使用されています");
		}

		Employee employee = authentication.getEmployee();
		employee.setUpdatedAt(LocalDateTime.now());
		employeeRepository.save(employee);
		authentication.setEmployee(employee);

		authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));
		authentication.setValidDate(LocalDate.parse("9999-12-31"));
		authenticationRepository.save(authentication);

		return authentication;
	}

	@Transactional
	public Authentication deleteAuthentication(Authentication authentication) {
		Employee employee = authentication.getEmployee();
		employee.setDeleteFlag(1);
		authentication.setEmployee(employee);

		return authenticationRepository.save(authentication);
	}

}

