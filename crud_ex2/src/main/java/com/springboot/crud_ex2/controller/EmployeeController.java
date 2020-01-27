package com.springboot.crud_ex2.controller;

import com.springboot.crud_ex2.exception.NotFoundException;
import com.springboot.crud_ex2.model.Employee;
import com.springboot.crud_ex2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> allEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws NotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found:::" + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @RequestBody Employee employeeDetail) throws NotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found:::" + employeeId));
        employee.setFirstName(employeeDetail.getFirstName());
        employee.setLastName(employeeDetail.getLastName());
        employee.setEmail(employeeDetail.getEmail());
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employee);
    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws NotFoundException {
        employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found:::" + employeeId));
        employeeRepository.deleteById(employeeId);
        return ResponseEntity.ok().build();
    }

}
