package com.ausy.employeesmanagement.ErrorHandler;

public class JobCategoryNotFoundException extends RuntimeException {

    private int id;

    public JobCategoryNotFoundException(int id)
    {
        this.id=id;
    }

    @Override
    public String toString() {
        return "No Job Category found with id "+id;
    }
}
