package com.ausy.employeesmanagement.Model.DAO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int departmentId) {
        this.id = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "departmentId=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
