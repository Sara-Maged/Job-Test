package com.example.JobManagementScv.service;

import com.example.JobManagementScv.model.Department;
import com.example.JobManagementScv.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> updateDepartment(Long id, Department department) {
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    existingDepartment.setDepartmentName(department.getDepartmentName());
                    existingDepartment.setLocation(department.getLocation());
                    return departmentRepository.save(existingDepartment);
                });
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
