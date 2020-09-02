package com.ausy.employeesmanagement.Model.DAO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jobcategories")
public class JobCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    //Getter si Setter

    public int getId() {
        return id;
    }

    public void setId(int jobCategoryId) {
        this.id = jobCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //toString

    @Override
    public String toString() {
        return "JobCategories{" +
                "jobCategoryId=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
