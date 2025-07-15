package com.example.spring_batch_demo.spring_batch_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_batch_demo.spring_batch_demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
