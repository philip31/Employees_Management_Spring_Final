package com.ausy.employeesmanagement.Controller;

import com.ausy.employeesmanagement.Model.DAO.Departments;
import com.ausy.employeesmanagement.Model.DAO.Employees;
import com.ausy.employeesmanagement.Model.DAO.JobCategories;
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
@RequestMapping("/jobcategories")
public class JobCategoriesController {

    @Autowired
    private EmployeesService employeesService;

    @PostMapping("/addJobCategory")
    public ResponseEntity<JobCategories> addJobCategory(@RequestBody JobCategories job) {
        if (job.getName() == null || job.getName().equals("")){
            throw new HttpMessageNotReadableException("no name");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(employeesService.addJobCategories(job));

    }

    @GetMapping("getAllJobCategories")
    public List<JobCategories> getAllJobCategories() {
        return employeesService.findAllJobCategories();
    }

    @GetMapping("/getJobCategoryById/{id}")
    public JobCategories findJobCategory(@PathVariable int id) {
        return employeesService.findJobCategory(id);
    }


    @DeleteMapping("/deleteJobCategory/{id}")
    public ResponseEntity<JSONObject> deleteJobCategory(@PathVariable int id) {

        employeesService.deleteJobCategory(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Deleted", "Yes");
        JSONObject body = new JSONObject();
        body.appendField("Deleted", "Yes");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(body);

    }
}
