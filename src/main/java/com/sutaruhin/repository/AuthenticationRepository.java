package com.sutaruhin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sutaruhin.entity.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, Integer>{

	List<Authentication> findByEmployeeDeleteFlag(int deleteFlag);

	Authentication findByEmployee_Id(Integer id);

	boolean existsByCode(String code);

	Authentication findByCode(String code);
}
