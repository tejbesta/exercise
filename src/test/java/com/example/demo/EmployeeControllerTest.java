package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.resolver.Mutation;
import com.example.demo.resolver.Query;

@SpringBootTest
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeController employeecontroller;
	
	@Autowired
	Mutation mutation;
	
	@Autowired
	Query query;
	
	@Test
	public void getAllEmployeeeTest() {
		Employee emp=new Employee(1,"Ram","Durg","ram1@gmail.com");
		List<Employee> list=new ArrayList<>();
		employeeController.getAllEmployees();
		list.add(emp);
		Mockito.when(employeeRepository.findAll()).thenReturn(list);
	}
		
	@Test
	public void createTest() {
		Employee emp = new Employee(1,"Ram","Durg","ram1@gmail.com");
		employeeController.create(emp);
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);
	}
	@Test
	public void getEmployeeByIdTest() {
		Employee emp = new Employee(1,"Ram","Durg","ram1@gmail.com");
		Optional<Employee> opt = Optional.of(emp);
		Mockito.when(employeeRepository.findById((long) 1)).thenReturn(opt);
		
		
	}
	@Test
	public void getEmployeeByIdExceptionTest() {
		try {
			Employee emp = new Employee(1,"Ram","Durg","ram1@gmail.com");
			Mockito.when(employeeRepository.findById((long) 1)).thenThrow(new NullPointerException());
		}catch(Exception e) {}
	}
	@Test
	public void deleteTest() {
//		employeeRepository.deleteById((long) 1);
		try {
			employeeController.delete(1);
		} catch (Exception e) {
		}
		
	}
	@Test
	public void deleteExceptionTest() {
		employeeRepository.delete(null);
		//Mockito.when(employeeRepository.delete(null)).thenThrow(new NullPointerException());
	}
	@Test
	public void updateTest() {
		Employee emp = new Employee(1,"Ram","Darg","ram1@gmail.com");
		employeeRepository.save(emp);
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);
	}
	
	
	@Test
	public void createEmployeeTest() {
		mutation.createEmployee("Ram", "Durg", "ram1@gmail.com");
		
	}
	@Test
	public void updateEmployeeTest() {
		mutation.updateEmployee((long) 1, "Ram", "Darg","ram1@gmail.com" );
	}
	@Test
	public void deleteEmployeeTest() {
		mutation.deleteEmployee((long) 1);
	}
	@Test
	public void getAllEmployeesTest() {
		query.getAllEmployees();
	}
}
