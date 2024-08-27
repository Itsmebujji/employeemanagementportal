package com.example.employeemanagementportal.controller

import com.example.employeemanagementportal.entities.Employee
import com.example.employeemanagementportal.service.EmployeeService
import spock.lang.Specification

class EmployeeControllerTest extends Specification {

    EmployeeService employeeService=Mock();
    EmployeeController employeeController = new EmployeeController(employeeService);

    def "get employees details function test"() {
        given:"no args"
        and:
        def empResponse = List.of(new Employee("1" as long,"Vineethkumar","Yalamanchi","vk@gmail.com","7659059530","Srikakulam, AndhraPradesh, India", "Merchandise", 35000));
        and:
        employeeService.getEmployeesDetails() >> empResponse;
        when:
        def response = employeeController.getEmployeesDetails();
        then:
        response==empResponse
    }
}
