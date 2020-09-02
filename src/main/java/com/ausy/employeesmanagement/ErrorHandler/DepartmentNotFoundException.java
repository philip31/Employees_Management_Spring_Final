package com.ausy.employeesmanagement.ErrorHandler;

public class DepartmentNotFoundException extends RuntimeException {

    private int id;

    public DepartmentNotFoundException(int id)
    {
        this.id=id;
    }

    @Override
    public String toString() {
        return "No Department found with id "+id;
    }
}
