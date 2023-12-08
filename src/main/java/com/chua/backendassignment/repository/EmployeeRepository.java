package com.chua.backendassignment.repository;

import com.chua.backendassignment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Employee> findByGroupId(Long postId);
}
