package com.chua.backendassignment.repository;

import com.chua.backendassignment.dto.EmployeeDto;
import com.chua.backendassignment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByGroupId(Long postId);

    List<Employee> deleteByGroupId(Long postId);
}
