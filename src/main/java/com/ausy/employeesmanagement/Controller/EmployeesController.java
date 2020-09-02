package com.ausy.employeesmanagement.Controller;

import com.ausy.employeesmanagement.Mapper.EmployeeMapper;
import com.ausy.employeesmanagement.Model.DAO.Employees;
import com.ausy.employeesmanagement.Model.DTO.EmployeesDTO;
import com.ausy.employeesmanagement.Service.EmployeesService;
import com.fasterxml.jackson.annotation.JsonAlias;
import net.minidev.json.JSONObject;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    //employees

        //POST

    @PostMapping("/addEmployee")
    public Employees addEmployee(@RequestBody Employees emp)
    {
            return employeesService.addEmployee(emp);
    }

    @PostMapping("/addEmployee/{idDep}/{idCat}")
    public ResponseEntity<Employees> addEmployee(@RequestBody Employees emp, @PathVariable int idDep,@PathVariable int idCat) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeesService.addEmployee(emp, idDep, idCat));
    }

        //GET

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employees> findEmployee(@PathVariable int id){

        return ResponseEntity.status(HttpStatus.FOUND).body(employeesService.findEmployee(id));


    }
    @GetMapping("/getAllEmployees")
    public List<Employees> getAllEmployees(){
        return employeesService.findAllEmployees();
    }
    @GetMapping("/getAllEmployeesDTO")
    public List<EmployeesDTO> getAllEmployeesDTO(){
        EmployeeMapper mapper = new EmployeeMapper();
        List<EmployeesDTO> employeesDTOList = new ArrayList<EmployeesDTO>();
        List<Employees> employeesList = getAllEmployees();

        for(Employees e : employeesList )
            employeesDTOList.add(mapper.convertToEmployeesDto(e));
        return employeesDTOList;
    }

    @GetMapping("/getEmployeesByDepartment/{id}")
    public List<Employees> getEmployeesByDepartment(@PathVariable int id){
        return employeesService.getEmployeesByDepartment(id);
    }
    @GetMapping("/getEmployeesByJob/{id}")
    public List<Employees> getEmployeesByJob(@PathVariable int id){
        return employeesService.getEmployeesByJob(id);
    }
    @GetMapping("/getEmployeesByDepartmentAndJob/{idDep}/{idJob}")
    public List<Employees> getEmployeesByDepartmentAndJob(@PathVariable int idDep, @PathVariable int idJob){
        List<Employees> employeesListByDep= employeesService.getEmployeesByDepartment(idDep);
        List<Employees> employeesListByJob= employeesService.getEmployeesByJob(idDep);

        employeesListByDep.retainAll(employeesListByJob);


        return employeesListByDep;
    }

    //   PUT
    /* in body-ul de request se pun doar campurile pe care doresti sa le updatezi

    ex: {
            "lastName":"Valentin",
            "department":"2",
            "endDate":"12-12-2020
        }

        Atentie formatul datei dd-MM-yyyy
    */

    @PutMapping("/updateEmployee/{id}")
    public Employees updateEmployee(@RequestBody JSONObject emp, @PathVariable int id){
        return employeesService.updateEmployee(id,emp);
    }

        //DELETE
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<JSONObject> deleteEmployee(@PathVariable int id){

        employeesService.deleteEmployee(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Deleted","Yes");
        JSONObject body = new JSONObject();
        body.appendField("Deleted","Yes");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(body);

    }






}
