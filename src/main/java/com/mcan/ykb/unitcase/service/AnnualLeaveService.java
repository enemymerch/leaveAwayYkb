package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.dao.IGenericDao;
import com.mcan.ykb.unitcase.exception.LeaveRequestException;
import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;
import com.mcan.ykb.unitcase.model.Employee;
import com.mcan.ykb.unitcase.utils.Constants;
import com.mcan.ykb.unitcase.utils.DateUtils;
import com.mcan.ykb.unitcase.utils.RequestCheckResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AnnualLeaveService implements IAnnualLeaveService{

    @Autowired
    private IEmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(AnnualLeaveService.class);
    private IGenericDao<Employee> employeeDao;
    private IGenericDao<AnnualLeaveRequest> requestDao;

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
        // calculate the working days between starting and ending dates of the leave request
        checkAndCorrectTotalWorkingDays(request);
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


    private void checkAndCorrectTotalWorkingDays(AnnualLeaveRequest request) {
        long workingDays = DateUtils.getWorkingDaysBetweenTwoDates(request.getLeaveStartDate(), request.getLeaveEndDate());
        long natiaolHolidays = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(request.getLeaveStartDate());
        for (String holiday : Constants.holidays) {
            try {
                if (!DateUtils.isWeekend(dateFormat.parse(year+"-"+holiday))) {
                    ++natiaolHolidays;
                }
            }catch (Exception e)  {
                // do nothing for now
            }
        }
        request.setTotalWorkDay((int)(workingDays-natiaolHolidays));
    }


    private RequestCheckResult getRequestCheckResult(AnnualLeaveRequest leaveRequest){
        RequestCheckResult requestCheckResult = new RequestCheckResult();

        checkEployeesRemainingLeaveDays(requestCheckResult, leaveRequest);
        checkLeaveRequestsStartDate(requestCheckResult, leaveRequest);
        checkLeaveRequestsDateInterval(requestCheckResult, leaveRequest);
        checkLeaveRequestsTotalWorkDay(requestCheckResult, leaveRequest);
        checkLeaveRequestsStatus(requestCheckResult, leaveRequest);

        return requestCheckResult;
    }

    private void checkEployeesRemainingLeaveDays(RequestCheckResult requestCheckResult, AnnualLeaveRequest leaveRequest) {
        // going to check if the employee has leave rights
        long remainingDays =  employeeService.getRemainingAnnulLeaveDayCount(leaveRequest.getEmployee().getId());
        if (remainingDays>=leaveRequest.getTotalWorkDay()) {
            requestCheckResult.setAcceptable(false);
            requestCheckResult.setLeaveRequestException(new LeaveRequestException("Employee's remaining leave days is not enough"));
        }
    }

    private void checkLeaveRequestsStartDate(RequestCheckResult requestCheckResult, AnnualLeaveRequest leaveRequest) {
        // start Date must be bigger than current Date
        Date currentDate = new Date(System.currentTimeMillis());
        long dayInterval = DateUtils.getInterval(leaveRequest.getLeaveStartDate(), currentDate, TimeUnit.DAYS);
        if (dayInterval<1) {
            requestCheckResult.setAcceptable(false);
            requestCheckResult.setLeaveRequestException(new LeaveRequestException("Leave request must the assigned to future date"));
        }
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
