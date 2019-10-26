package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;

import java.util.List;

public interface IAnnualLeaveService {
    String getRemainingLeaveDay(long employeeId);

    AnnualLeaveRequest approve(long employeeId);

    AnnualLeaveRequest reject(long employeeId);

    List<AnnualLeaveRequest> findAll();

    AnnualLeaveRequest findById(long employeeId);

    AnnualLeaveRequest create(AnnualLeaveRequest action) throws LeaveRequestException;

    AnnualLeaveRequest update(AnnualLeaveRequest action);

    void deleteById (long employeeId);
}
