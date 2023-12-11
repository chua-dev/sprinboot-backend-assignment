package com.chua.backendassignment.repository;

import com.chua.backendassignment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long deptId);

    List<Employee> deleteByDepartmentId(Long deptId);
}
