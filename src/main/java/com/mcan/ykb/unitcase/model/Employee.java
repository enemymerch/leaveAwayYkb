package com.mcan.ykb.unitcase.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    @Type(type = "date")
    private Date startDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AnnualLeaveAction> annualLeaveActions;

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

    public Set<AnnualLeaveAction> getAnnualLeaveActions() {
        return annualLeaveActions;
    }

    public void setAnnualLeaveActions(Set<AnnualLeaveAction> annualLeaveActions) {
        this.annualLeaveActions = annualLeaveActions;
    }
}
