package com.example.employeemanagementportal.service;

import com.example.employeemanagementportal.entities.Employee;
import com.example.employeemanagementportal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesDetails() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        Optional<Employee> employee;
        if (id==0){
            return Optional.empty();
        }else {
            employee = employeeRepository.findById(id);
            return employee;
        }
    }

    public Employee createEmployee(Employee employee) {
        if (employee==null) {
            return null;
        }
        return employeeRepository.save(employee);
    }

    public ResponseEntity<List<Employee>> createEmployees(List<Employee> employees) {
        if(employees.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        List<Employee> listOfNewEmployees = employeeRepository.saveAll(employees);
        return ResponseEntity.ok(listOfNewEmployees);
    }


    public ResponseEntity<Employee> updateEmployeeById(Employee employee, int id) {
        if (id==0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            Employee updateEmployee = employeeRepository.findById(id).orElse(null);
            if(updateEmployee==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                updateEmployee.setName(employee.getName());
                updateEmployee.setSurname(employee.getSurname());
                updateEmployee.setPhone(employee.getPhone());
                updateEmployee.setAddress(employee.getAddress());
                updateEmployee.setDepartment(employee.getDepartment());
                updateEmployee.setSalary(employee.getSalary());
                employeeRepository.save(updateEmployee);
                return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
            }
        }
    }

    public ResponseEntity<Object> deleteEmployeeById(int id) {
        if (id==0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            employeeRepository.deleteById(id);
            return new ResponseEntity<>("Employee is deleted from database", HttpStatus.OK);
        }
    }
}
