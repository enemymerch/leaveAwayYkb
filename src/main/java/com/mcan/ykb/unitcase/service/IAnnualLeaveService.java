package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.model.AnnualLeaveAction;
import com.mcan.ykb.unitcase.model.Employee;

import java.util.List;

public interface IAnnualLeaveService {
    List<AnnualLeaveAction> findAll();
    AnnualLeaveAction findById(long id);
    AnnualLeaveAction create(AnnualLeaveAction action);
    AnnualLeaveAction update(AnnualLeaveAction action);
    void deleteById (long id);
}
