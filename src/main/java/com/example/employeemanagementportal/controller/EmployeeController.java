package com.example.employeemanagementportal.controller;

import com.example.employeemanagementportal.entities.Employee;
import com.example.employeemanagementportal.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/emp")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/getEmployeesDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesDetails() {
        return employeeService.getEmployeesDetails();
    }

    @GetMapping(value = "/getEmployeeById/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Employee> getEmployeeById(@PathVariable(name = "id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(value = "/createEmployee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PostMapping(value = "/createEmployees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<Employee> employees) {
        return employeeService.createEmployees(employees);
    }

    @PutMapping(value = "/updateEmployeeById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee, @RequestParam(name = "id") int id) {
        return employeeService.updateEmployeeById(employee,id);
    }

    @DeleteMapping(value = "/deleteEmployeeById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteEmployeeById(@RequestParam(name = "id") int id) {
        return employeeService.deleteEmployeeById(id);
    }

}

