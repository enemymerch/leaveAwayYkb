package com.mcan.ykb.unitcase.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class AnnualLeaveAction {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Date leaveStartDate;

    @Column
    @Type(type = "date")
    private Date leaveEndDate;

    @Column
    @Type(type = "date")
    private int totalWorkDay;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn
    private Employee employee;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(Date leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(Date leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public int getTotalWorkDay() {
        return totalWorkDay;
    }

    public void setTotalWorkDay(int totalWorkDay) {
        this.totalWorkDay = totalWorkDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
