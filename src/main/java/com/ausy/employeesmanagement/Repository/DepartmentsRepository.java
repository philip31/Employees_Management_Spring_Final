package com.ausy.employeesmanagement.Repository;

import com.ausy.employeesmanagement.Model.DAO.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments,Integer> {

    Departments findById(int id);
}
