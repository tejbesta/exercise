package com.example.demo.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class Mutation implements GraphQLMutationResolver {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	
	public Employee createEmployee(String fname,String lname,String email) {
		Employee employee=new Employee();
		employee.setFname(fname);
		employee.setLname(lname);
		employee.setEmail(email);
		return employeeRepository.save(employee);
		
	}
	public Employee  updateEmployee(Long id,String fname,String lname,String email) {
		Employee employee=new Employee();
		employee.setId(id);
		employee.setFname(fname);
		employee.setLname(lname);
		employee.setEmail(email);
		return employeeRepository.save(employee);
	}
	public Boolean deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return true;
	}

	
}

