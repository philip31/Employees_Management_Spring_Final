package com.ausy.employeesmanagement.Mapper;

import com.ausy.employeesmanagement.Model.DAO.Employees;
import com.ausy.employeesmanagement.Model.DTO.EmployeesDTO;
import com.ausy.employeesmanagement.Repository.DepartmentsRepository;
import com.ausy.employeesmanagement.Repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class EmployeeMapper {



        @Autowired
        private EmployeesRepository employeesRepository;


        public EmployeesDTO convertToEmployeesDto(Employees emp)
        {
            EmployeesDTO employeesDTO = new EmployeesDTO();

            employeesDTO.setFirstName(emp.getFirstName());
            employeesDTO.setLastName(emp.getLastName());
            employeesDTO.setEmail(emp.getEmail());

            if(emp.getDepartment() == null)
                employeesDTO.setDepartment(null);
            else
            employeesDTO.setDepartment(emp.getDepartment().getName());

            if(emp.getJobCategories() == null)
                employeesDTO.setJobCategory(null);
            else
            employeesDTO.setJobCategory(emp.getJobCategories().getName());

            employeesDTO.setTelephone(emp.getTelephone());

            return employeesDTO;
        }
    }

