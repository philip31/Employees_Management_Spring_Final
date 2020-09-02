package com.ausy.employeesmanagement.ErrorHandler;

import com.ausy.employeesmanagement.Model.DAO.Employees;

public class EmployeeNotFoundException extends RuntimeException {

    private int id;

    public EmployeeNotFoundException(int id)
    {
        this.id=id;
    }

    @Override
    public String toString() {
        return "No Employee found with id "+id;
    }
}
