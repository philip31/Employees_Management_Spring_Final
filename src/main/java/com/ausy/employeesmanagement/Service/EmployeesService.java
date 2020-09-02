package com.ausy.employeesmanagement.Service;

import com.ausy.employeesmanagement.ErrorHandler.DepartmentNotFoundException;
import com.ausy.employeesmanagement.ErrorHandler.EmployeeNotFoundException;
import com.ausy.employeesmanagement.ErrorHandler.JobCategoryNotFoundException;
import com.ausy.employeesmanagement.Model.DAO.Departments;
import com.ausy.employeesmanagement.Model.DAO.Employees;
import com.ausy.employeesmanagement.Model.DAO.JobCategories;
import com.ausy.employeesmanagement.Repository.DepartmentsRepository;
import com.ausy.employeesmanagement.Repository.EmployeesRepository;
import com.ausy.employeesmanagement.Repository.JobCategoriesRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private DepartmentsRepository departmentsRepository;
    @Autowired
    private JobCategoriesRepository jobCategoriesRepository;

//employee
    public Employees addEmployee(Employees emp) {
            return this.employeesRepository.save(emp);
    }

    public Employees addEmployee(Employees emp, int idDep, int idJob){
        Departments dep = this.departmentsRepository.findById(idDep);
        JobCategories job = this.jobCategoriesRepository.findById(idJob);

        if (dep == null){
            throw new DepartmentNotFoundException(idDep);
        }
        if(job == null){
            throw new JobCategoryNotFoundException(idJob);
        }

        emp.setDepartment(dep);
        emp.setJobCategories(job);

        return addEmployee(emp);
    }
    public List<Employees> findAllEmployees(){

        return this.employeesRepository.findAll();
    }

    public Employees findEmployee(int id){
        Employees emp = this.employeesRepository.findById(id);
        if(emp == null){
            throw new EmployeeNotFoundException(id);
        }
        return emp;
    }

    public Employees updateEmployee(int id, JSONObject emp){
        Employees empDataBase = this.employeesRepository.findById(id);
        if(empDataBase == null){
            throw new EmployeeNotFoundException(id);
        }



            Iterator<String> keys= emp.keySet().iterator();
            String next;
            while(keys.hasNext()){
                next = keys.next();
                switch(next){
                    case "firstName":{
                        empDataBase.setFirstName(emp.get(next).toString());
                        break;
                    }
                    case "lastName":{
                        empDataBase.setLastName(emp.get(next).toString());
                        break;
                    }
                    case "isManager":{
                        empDataBase.setIsManager(Integer.parseInt(emp.get(next).toString()));
                        break;
                    }
                    case "startDate":{
                        try{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            empDataBase.setStartDate(LocalDate.parse(emp.get(next).toString(),formatter));
                        }catch(Exception e){
                            throw new HttpMessageNotReadableException("probleme de formatare");
                        }
                        break;
                    }
                    case "endDate":{
                        try{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            empDataBase.setEndDate(LocalDate.parse(emp.get(next).toString(),formatter));

                        }catch(Exception e){
                            throw new HttpMessageNotReadableException("probleme de formatare");
                        }
                        break;
                    }
                    case "active":{
                        empDataBase.setActive(Integer.parseInt(emp.get(next).toString()));
                        break;
                    }
                    case "address":{
                        empDataBase.setAddress(emp.get(next).toString());
                        break;
                    }
                    case "CP":{
                        empDataBase.setCP(emp.get(next).toString());
                        break;
                    }
                    case "telephone":{
                        empDataBase.setTelephone(emp.get(next).toString());
                        break;
                    }
                    case "birthday":{
                        try{
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            empDataBase.setBirthday(LocalDate.parse(emp.get(next).toString(),formatter));
                        }catch(Exception e){
                            throw new HttpMessageNotReadableException("probleme de formatare");
                        }
                        break;
                    }
                    case "noChildren":{
                        empDataBase.setNoChildren(Integer.parseInt(emp.get(next).toString()));
                        break;
                    }
                    case "salary":{
                        empDataBase.setSalary(Double.parseDouble(emp.get(next).toString()));
                        break;
                    }
                    case "studies":{
                        empDataBase.setStudies(emp.get(next).toString());
                        break;
                    }
                    case "socialSecurityNumber":{
                        empDataBase.setSocialSecurityNumber(emp.get(next).toString());
                        break;
                    }
                    case "hasDrivingLicence":{
                        empDataBase.setHasDrivingLicence(Integer.parseInt(emp.get(next).toString()));
                        break;
                    }
                    case "department":{
                        Departments dep = this.departmentsRepository.findById(Integer.parseInt(emp.get(next).toString()));
                        if(dep == null){
                            throw new DepartmentNotFoundException(Integer.parseInt(emp.get(next).toString()));
                        }
                        empDataBase.setDepartment(this.departmentsRepository.findById(Integer.parseInt(emp.get(next).toString())));
                        break;
                    }
                    case "jobCategories":{
                        JobCategories job = this.jobCategoriesRepository.findById(Integer.parseInt(emp.get(next).toString()));
                        if(job == null){
                            throw new JobCategoryNotFoundException(Integer.parseInt(emp.get(next).toString()));
                        }
                        empDataBase.setJobCategories(this.jobCategoriesRepository.findById(Integer.parseInt(emp.get(next).toString())));
                        break;
                    }
                    case "email":{
                        empDataBase.setEmail(emp.get(next).toString());
                        break;
                    }
                    default:{
                        throw new HttpMessageNotReadableException(next);

                    }
                }
            }
            this.employeesRepository.save(empDataBase);
            return empDataBase;

    }

    public void deleteEmployee(int id){
        this.employeesRepository.delete(findEmployee(id));
    }

    public List<Employees> getEmployeesByDepartment(int id){
       return this.employeesRepository.findByDepartment(findDepartment(id));
    }
    public List<Employees> getEmployeesByJob(int id){
        return this.employeesRepository.findByJobCategories(findJobCategory(id));
    }


    //departments
    public Departments addDepartments(Departments dep){
        return this.departmentsRepository.save(dep);
    }
    public List<Departments> findAllDepartments(){
        return this.departmentsRepository.findAll();
    }
    public Departments findDepartment(int id){
        Departments dep =this.departmentsRepository.findById(id);
        if(dep == null){
            throw new DepartmentNotFoundException(id);
        }
        return dep;
    }
    public void deleteDepartment(int id){
        List<Employees> e = getEmployeesByDepartment(id);
        for(Employees emp : e){
            emp.setDepartment(null);
            this.employeesRepository.save(emp);
        }

        this.departmentsRepository.delete(findDepartment(id));
    }
//jobcategories
    public JobCategories addJobCategories(JobCategories job){
        return this.jobCategoriesRepository.save(job);
    }
    public List<JobCategories> findAllJobCategories(){
       return this.jobCategoriesRepository.findAll();
    }
    public JobCategories findJobCategory(int id){
        JobCategories job = this.jobCategoriesRepository.findById(id);
        if( job == null){
            throw new JobCategoryNotFoundException(id);
        }
        return job;
    }
    public void deleteJobCategory(int id){
        List<Employees> e = getEmployeesByJob(id);
        for(Employees emp : e){
            emp.setJobCategories(null);
            this.employeesRepository.save(emp);
        }

        this.jobCategoriesRepository.delete(findJobCategory(id));
    }
}
