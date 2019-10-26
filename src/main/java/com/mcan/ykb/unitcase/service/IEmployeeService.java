package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;
import com.mcan.ykb.unitcase.model.Employee;

import java.util.List;

public interface IEmployeeService {
    long getRemainingAnnulLeaveDayCount(long employeeId);

    List<AnnualLeaveRequest> getPendingLeaves(long id);

    List<AnnualLeaveRequest> getRejectedLeaves(long id);

    List<AnnualLeaveRequest> getApprovedLeaves(long id);

    List<Employee> findAll();

    Employee findById(long id);
    
    Employee create(Employee employee);

    Employee update(Employee employee);

    void deleteById (long id);
}
