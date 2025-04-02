package com.example.employee_talkdesk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee_talkdesk.entity.Employee;

@Repository
public interface Employee_Repository extends JpaRepository<Employee, Long>{

}
