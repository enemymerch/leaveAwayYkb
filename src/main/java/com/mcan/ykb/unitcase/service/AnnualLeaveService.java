package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.dao.IGenericDao;
import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;
import com.mcan.ykb.unitcase.model.Employee;
import com.mcan.ykb.unitcase.utils.Constants;
import com.mcan.ykb.unitcase.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class AnnualLeaveService implements IAnnualLeaveService{

    Logger logger = LoggerFactory.getLogger(AnnualLeaveService.class);
    private IGenericDao<Employee> employeeDao;
    private IGenericDao<AnnualLeaveRequest> requestDao;



    @Override
    public String getRemainingLeaveDay(long id) {
        return null;
    }

    @Override
    public AnnualLeaveRequest approve(long id) {
        AnnualLeaveRequest annualLeaveRequest = findById(id);
        annualLeaveRequest.setStatus(Constants.LeaveRequestStatus.APPROVED);
        return update(annualLeaveRequest);
    }

    @Override
    public AnnualLeaveRequest reject(long id) {
        AnnualLeaveRequest annualLeaveRequest = findById(id);
        annualLeaveRequest.setStatus(Constants.LeaveRequestStatus.REJECTED);
        return update(annualLeaveRequest);
    }

    @Override
    public List<AnnualLeaveRequest> findAll() {
        return requestDao.findAll();
    }

    @Override
    public AnnualLeaveRequest findById(long id) {
        return requestDao.findOne(id);
    }

    @Override
    public AnnualLeaveRequest create(AnnualLeaveRequest request) throws LeaveRequestException {
        RequestCheckResult result = getRequestCheckResult(request);
        if (!result.isAcceptable()) {
            throw result.getLeaveRequestException();
        }
        requestDao.save(request);
        return request;
    }

    @Override
    public AnnualLeaveRequest update(AnnualLeaveRequest request) {
        getRequestCheckResult(request);
        requestDao.update(request);
        return request;
    }

    @Override
    public void deleteById(long id) {
        requestDao.deleteById(id);
    }

    @SuppressWarnings("unused")
    @Autowired
    public void setRequestDao(IGenericDao<AnnualLeaveRequest> requestDao) {
        this.requestDao = requestDao;
        this.requestDao.setClazz(AnnualLeaveRequest.class);
    }

    @SuppressWarnings("unused")
    @Autowired
    public void setEmployeeDao(IGenericDao<Employee> employeeDao){
        this.employeeDao = employeeDao;
        this.employeeDao.setClazz(Employee.class);
    }

    private RequestCheckResult getRequestCheckResult(AnnualLeaveRequest leaveRequest){
        RequestCheckResult requestCheckResult = new RequestCheckResult();

        checkLeaveRequestsDateInterval(requestCheckResult, leaveRequest);
        checkLeaveRequestsTotalWorkDay(requestCheckResult, leaveRequest);
        checkLeaveRequestsStatus(requestCheckResult, leaveRequest);

        return requestCheckResult;
    }
    private void checkLeaveRequestsDateInterval(RequestCheckResult requestCheckResult, AnnualLeaveRequest leaveRequest){
        // day difference between endDate and starDate must be bigger than zero
        long dayInterval = DateUtils.getInterval(leaveRequest.getLeaveEndDate(), leaveRequest.getLeaveStartDate(), TimeUnit.DAYS);
        if (dayInterval<=0){
            requestCheckResult.setAcceptable(false);
            requestCheckResult.setLeaveRequestException(new LeaveRequestException("Start Date cannot be bigger than or equal to end date."));
        }
    }
    private void checkLeaveRequestsTotalWorkDay(RequestCheckResult requestCheckResult, AnnualLeaveRequest leaveRequest){
        // total work day cannot be bigger than day interval between endDate and startDate
        long dayInterval = DateUtils.getInterval(leaveRequest.getLeaveEndDate(), leaveRequest.getLeaveStartDate(), TimeUnit.DAYS);
        if(dayInterval < leaveRequest.getTotalWorkDay()) {
            requestCheckResult.setAcceptable(false);
            requestCheckResult.setLeaveRequestException(new LeaveRequestException("Total work day cannot be bigger than day interval between endDate and startDate."));
        }
    }
    private void checkLeaveRequestsStatus(RequestCheckResult requestCheckResult, AnnualLeaveRequest leaveRequest){
        // status of request must be pending
        if(!Constants.LeaveRequestStatus.PENDING.equals(leaveRequest.getStatus())) {
            requestCheckResult.setAcceptable(false);
            requestCheckResult.setLeaveRequestException(new LeaveRequestException("Status of request must be pending."));
        }
    }
}
