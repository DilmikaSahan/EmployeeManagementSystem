package com.employeeMS.employeeMS.repository;

import com.employeeMS.employeeMS.entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface employeeRepo extends JpaRepository<employee,Integer> {
}
