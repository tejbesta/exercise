package com.example.demo.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/emp")
	public List<Employee> getAllEmployees(){
		return (List<Employee>) employeeRepository.findAll();
	}
	
	@PostMapping("/emp/add")
	public Employee create(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") long id) throws ResourceNotFoundException{
		Employee employee= employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		return ResponseEntity.ok().body(employee);
	}
	
	@PutMapping("/emp/{id}")
	public ResponseEntity<Employee> update(@PathVariable(value="id") long id, @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee= employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		employee.setFname(employeeDetails.getFname());
		employee.setLname(employeeDetails.getLname());
		employee.setEmail(employeeDetails.getEmail());
		employeeRepository.save(employee);
		return ResponseEntity.ok().body(employee);
	}
	
	@DeleteMapping("/emp/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value="id") long id) throws ResourceNotFoundException {
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		employeeRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
}
