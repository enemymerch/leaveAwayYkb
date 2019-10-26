package com.mcan.ykb.unitcase.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    @Type(type = "date")
    private Date startDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<AnnualLeaveRequest> annualLeaveRequests;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Set<AnnualLeaveRequest> getAnnualLeaveRequests() {
        return annualLeaveRequests;
    }

    public void setAnnualLeaveRequests(Set<AnnualLeaveRequest> annualLeaveRequests) {
        this.annualLeaveRequests = annualLeaveRequests;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
