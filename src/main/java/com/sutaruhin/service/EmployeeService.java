package com.sutaruhin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sutaruhin.entity.Employee;
import com.sutaruhin.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findById(Integer id) {
    	Employee employee = employeeRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("社員が存在しません"));
        return employee;
    }
}