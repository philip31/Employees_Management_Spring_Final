package com.ausy.employeesmanagement.Repository;

import com.ausy.employeesmanagement.Model.DAO.JobCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoriesRepository extends JpaRepository<JobCategories,Integer> {
    JobCategories findById(int id);
}
