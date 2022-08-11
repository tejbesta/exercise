package com.example.demo.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	private EmployeeRepository employeeRepository;
	

	
	public Iterable<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	public Employee findEmployee(Long id) {
		return employeeRepository.findById(id).orElseGet(null);
	}
}
