package com.mcan.ykb.unitcase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import net.bytebuddy.build.ToStringPlugin;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class AnnualLeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Type(type = "date")
    private Date leaveStartDate;

    @Column
    @Type(type = "date")
    private Date leaveEndDate;

    @Column
    private int totalWorkDay;

    @Column
    private String status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn
    private Employee employee;

    public AnnualLeaveRequest() {
    }

    public AnnualLeaveRequest(Date leaveStartDate, Date leaveEndDate, int totalWorkDay, String status, Employee employee){
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.totalWorkDay = totalWorkDay;
        this.status = status;
        this.employee = employee;
    }

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

    @Override
    public String toString() {
        return "AnnualLeaveRequest{" +
                "id=" + id +
                ", leaveStartDate=" + leaveStartDate +
                ", leaveEndDate=" + leaveEndDate +
                ", totalWorkDay=" + totalWorkDay +
                ", status='" + status + '\'' +
                '}';
    }
}
