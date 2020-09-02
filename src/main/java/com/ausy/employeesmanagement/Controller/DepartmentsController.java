package com.ausy.employeesmanagement.Controller;

import com.ausy.employeesmanagement.Model.DAO.Departments;
import com.ausy.employeesmanagement.Model.DAO.Employees;
import com.ausy.employeesmanagement.Service.EmployeesService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    @Autowired
    private EmployeesService employeesService;


    @PostMapping("/addDepartment")
    public ResponseEntity<Departments> addDepartment(@RequestBody Departments dep) {
        if (dep.getName() == null || dep.getName().equals("")){
            throw new HttpMessageNotReadableException("no name");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeesService.addDepartments(dep));

    }

    @GetMapping("getAllDepartments")
    public List<Departments> getAllDepartments() {
        return employeesService.findAllDepartments();
    }

    @GetMapping("/getDepartmentById/{id}")
    public Departments findDepartment(@PathVariable int id) {
        return employeesService.findDepartment(id);
    }


    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<JSONObject> deleteDepartment(@PathVariable int id) {

        employeesService.deleteDepartment(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Deleted", "Yes");
        JSONObject body = new JSONObject();
        body.appendField("Deleted", "Yes");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(body);

    }
}

