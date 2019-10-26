package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.dao.IGenericDao;
import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;
import com.mcan.ykb.unitcase.model.Employee;
import com.mcan.ykb.unitcase.utils.Constants;
import com.mcan.ykb.unitcase.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IAnnualLeaveService annualLeaveService;

    private IGenericDao<Employee> employeeDao;

    @Override
    public List<AnnualLeaveRequest> getPendingLeaves(long employeeId) {
        Employee employee = employeeDao.findOne(employeeId);
        return filterPendingLeaves(employee.getAnnualLeaveRequests());
    }
    @Override
    public List<AnnualLeaveRequest> getRejectedLeaves(long employeeId) {
        Employee employee = employeeDao.findOne(employeeId);
        return filterRejectedLeaves(employee.getAnnualLeaveRequests());
    }

    @Override
    public List<AnnualLeaveRequest> getApprovedLeaves(long employeeId) {
        Employee employee = employeeDao.findOne(employeeId);
        return filterApprovedLeaves(employee.getAnnualLeaveRequests());
    }
    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Autowired
    @SuppressWarnings("unused")
    public void setEmployeeDao(IGenericDao<Employee> employeeDao){
        this.employeeDao = employeeDao;
        this.employeeDao.setClazz(Employee.class);
    }

    @Override
    public long getRemainingAnnulLeaveDayCount(long employeeId) {
        Employee employee = findById(employeeId);
        long totalLeave =  getEmployeesAnnualLeaveTotalDayCount(employee);
        List<AnnualLeaveRequest> approvedLeaveRequests = filterApprovedLeaves(employee.getAnnualLeaveRequests());
        long totalApprovedLeaveDayCount = 0;
        for (AnnualLeaveRequest leaveRequest : approvedLeaveRequests) {
            totalApprovedLeaveDayCount += leaveRequest.getTotalWorkDay();
        }
        return totalLeave - totalApprovedLeaveDayCount;
    }

    @Override
    public Employee findById(long employeeId) {
        return employeeDao.findOne(employeeId);
    }

    @Override
    public Employee create(Employee employee) {
        try {
            employeeDao.save(employee);
        } catch (Exception e) {
            return null;
        }
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        try {
            employeeDao.update(employee);
        } catch (Exception e) {
            return null;
        }
        return employee;

    }

    @Override
    public void deleteById(long employeeId) {
        employeeDao.deleteById(employeeId);
    }

    private long getEmployeesAnnualLeaveTotalDayCount(Employee employee){
        Date currentDate = new Date(System.currentTimeMillis());
        long intervalInYears = DateUtils.getIntervalInYears(currentDate, employee.getStartDate());

        // if employee is working less than a year , day count is zero(0)
        long leaveDayCount = 0;
        if ( intervalInYears >= 1 && intervalInYears <= 5 ) {
            // between 1 - 5(included) :15
            leaveDayCount = 15;
        } else if (intervalInYears > 5  && intervalInYears <+= 10) {
            //between 5 - 10(included) : 18
            leaveDayCount = 18;
        } else if (intervalInYears > 10) {
            // bigger than 10 : 24
            leaveDayCount = 24;
        }
        return leaveDayCount;
    }
    private List<AnnualLeaveRequest> filterApprovedLeaves(Set<AnnualLeaveRequest> annualLeaveRequests){
        return filterLeavesByStatus(annualLeaveRequests, Constants.LeaveRequestStatus.APPROVED);
    }

    private List<AnnualLeaveRequest> filterRejectedLeaves(Set<AnnualLeaveRequest> annualLeaveRequests){
        return filterLeavesByStatus(annualLeaveRequests, Constants.LeaveRequestStatus.REJECTED);
    }
    private List<AnnualLeaveRequest> filterPendingLeaves(Set<AnnualLeaveRequest> annualLeaveRequests){
        return filterLeavesByStatus(annualLeaveRequests, Constants.LeaveRequestStatus.PENDING);
    }
    private List<AnnualLeaveRequest> filterLeavesByStatus(Set<AnnualLeaveRequest> annualLeaveRequests, String status){
        if (status == null) return null;

        List<AnnualLeaveRequest> filteredLeaves = new ArrayList<AnnualLeaveRequest>();
        for(AnnualLeaveRequest annualLeaveRequest : annualLeaveRequests) {
            if (status.equals(annualLeaveRequest.getStatus())) {
                filteredLeaves.add(annualLeaveRequest);
            }
        }
        return filteredLeaves;
    }
}
