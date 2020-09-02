package com.ausy.employeesmanagement.Repository;

import com.ausy.employeesmanagement.Model.DAO.Departments;
import com.ausy.employeesmanagement.Model.DAO.Employees;
import com.ausy.employeesmanagement.Model.DAO.JobCategories;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    List<Employees> findAllBy();
    Employees findById(int id);

    List<Employees> findByDepartment(Departments d);
    List<Employees> findByJobCategories(JobCategories j);


}
