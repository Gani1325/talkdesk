package com.example.employee_talkdesk.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employee_talkdesk.Service.Employee_Service;
import com.example.employee_talkdesk.entity.Employee;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class Employee_Controller {

    private final Employee_Service service;

    public Employee_Controller(Employee_Service service) {
        super();
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "Application has been reached";
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = service.saveEmployee(employee);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("code", "EMPLOYEE_CREATED");
            response.put("message", "Employee created successfully");
            response.put("data", savedEmployee);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("status", "error");
            errorResponse.put("code", "EMPLOYEE_CREATION_FAILED");
            errorResponse.put("message", "Failed to create employee");
            errorResponse.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = service.getEmployeeById(id);
        if (employee.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("code", "EMPLOYEE_FOUND");
            response.put("data", employee.get());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("status", "error");
            errorResponse.put("code", "EMPLOYEE_NOT_FOUND");
            errorResponse.put("message", "Employee with ID " + id + " not found");
            errorResponse.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/getEmployeeDetails")
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        List<Employee> employees = service.getAllEmployees();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("code", "EMPLOYEES_RETRIEVED");
        response.put("data", employees);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Long id) {
        try {
            service.deleteEmployee(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("code", "EMPLOYEE_DELETED");
            response.put("message", "Employee deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("status", "error");
            errorResponse.put("code", "EMPLOYEE_DELETION_FAILED");
            errorResponse.put("message", "Failed to delete employee");
            errorResponse.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PutMapping("/updateEmpById/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(
            @PathVariable Long id, @RequestBody Employee updatedEmployee) {
        try {
            Optional<Employee> existingEmployee = service.getEmployeeById(id);

            if (existingEmployee.isPresent()) {
                Employee employeeToUpdate = existingEmployee.get();
                
                // Updating fields
                employeeToUpdate.setName(updatedEmployee.getName());
                employeeToUpdate.setDepartment(updatedEmployee.getDepartment());

                Employee savedEmployee = service.saveEmployee(employeeToUpdate);

                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("code", "EMPLOYEE_UPDATED");
                response.put("message", "Employee updated successfully");
                response.put("data", savedEmployee);
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
//                errorResponse.put("status", "error");
                errorResponse.put("code", "EMPLOYEE_NOT_FOUND");
                errorResponse.put("message", "Employee with ID " + id + " not found");
                errorResponse.put("timestamp", System.currentTimeMillis());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("code", "EMPLOYEE_UPDATE_FAILED");
            errorResponse.put("message", "Failed to update employee");
            errorResponse.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    


   

      
    }


