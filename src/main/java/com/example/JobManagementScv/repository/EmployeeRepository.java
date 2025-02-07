package com.example.JobManagementScv.repository;

import com.example.JobManagementScv.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByFirstNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Employee> findByFirstNameContainingIgnoreCaseAndDepartment_DepartmentId(String name, Long departmentId, Pageable pageable);

    Page<Employee> findByDepartment_DepartmentId(Long departmentId, Pageable pageable);
}
