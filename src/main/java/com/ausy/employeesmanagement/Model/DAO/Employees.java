package com.ausy.employeesmanagement.Model.DAO;

import com.ausy.employeesmanagement.ErrorHandler.EmployeeNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name="isManager")
    @Value("${isManager:0}")
    private int isManager;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @Value("${endDate:NULL}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    @Value("${active:1}")
    private int active;
    private String address;
    private String CP;
    private String telephone;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    @Value("${noChildren:0}")
    private int noChildren;
    private double salary;
    @Value("${studies:\"\"}")
    private String studies;
    @Value("${socialSecurityNumber:\"\"}")
    private String socialSecurityNumber;
    @Value("${hasDrivingLicence:0}")
    private int hasDrivingLicence;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;

    @ManyToOne
    @JoinColumn(name = "job_categories_id")
    private JobCategories jobCategories;


    //Getter si Setter


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getNoChildren() {
        return noChildren;
    }

    public void setNoChildren(int noChildren) {
        this.noChildren = noChildren;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public int getHasDrivingLicence() {
        return hasDrivingLicence;
    }

    public void setHasDrivingLicence(int hasDrivingLicence) {
        this.hasDrivingLicence = hasDrivingLicence;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public JobCategories getJobCategories() {
        return jobCategories;
    }

    public void setJobCategories(JobCategories jobCategories) {
        this.jobCategories = jobCategories;
    }

    public void updateEmployee(Employees e){


    }

    //toString

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isManager=" + isManager +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", active=" + active +
                ", address='" + address + '\'' +
                ", CP='" + CP + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", noChildren=" + noChildren +
                ", salary=" + salary +
                ", studies='" + studies + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", hasDrivingLicence=" + hasDrivingLicence +
                ", department=" + department +
                ", jobCategories=" + jobCategories +
                '}';
    }
}

