package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;
import com.mcan.ykb.unitcase.exception.LeaveRequestException;

import java.util.List;

public interface IAnnualLeaveService {
    AnnualLeaveRequest approve(long employeeId);

    AnnualLeaveRequest reject(long employeeId);

    List<AnnualLeaveRequest> findAll();

    AnnualLeaveRequest findById(long employeeId);

    AnnualLeaveRequest create(AnnualLeaveRequest action) throws LeaveRequestException;

    AnnualLeaveRequest update(AnnualLeaveRequest action);

    void deleteById (long employeeId);
}
