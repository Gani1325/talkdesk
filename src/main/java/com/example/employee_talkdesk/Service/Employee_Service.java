package com.example.employee_talkdesk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_talkdesk.Repository.Employee_Repository;
import com.example.employee_talkdesk.entity.Employee;


@Service
public class Employee_Service {
	
	 @Autowired
	    private Employee_Repository repository;
	 
	 	

	    public Employee_Service(Employee_Repository repository) {
		super();
		this.repository = repository;
	}

		public Employee saveEmployee(Employee employee) {
	        return repository.save(employee);
	    }

	    public List<Employee> getAllEmployees() {
	        return repository.findAll();
	    }

	    public Optional<Employee> getEmployeeById(Long id) {
	        return repository.findById(id);
	    }

	    public void deleteEmployee(Long id) {
	        repository.deleteById(id);
	    }

}
