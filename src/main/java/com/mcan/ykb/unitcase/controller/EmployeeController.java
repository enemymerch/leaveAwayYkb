package com.mcan.ykb.unitcase.controller;

import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;
import com.mcan.ykb.unitcase.model.Employee;
import com.mcan.ykb.unitcase.service.IEmployeeService;
import com.mcan.ykb.unitcase.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "Employee Handler", description = "Employee CRUD and listing endpoints")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "Gets Employees leave requests by request status. Status can be : pending, aproved and rejected")
    @GetMapping("/{employeeId}/leave/{status}")
    public List<AnnualLeaveRequest> getLeavesByStatus(@PathVariable(required = true, name = "status") String status , @PathVariable(required = true, name = "employeeId") long employeeId){
        List<AnnualLeaveRequest> leaves = null;
        switch (status){
            case Constants.LeaveRequestStatus.PENDING:
                leaves = employeeService.getPendingLeaves(employeeId);
                break;
            case Constants.LeaveRequestStatus.REJECTED:
                leaves = employeeService.getRejectedLeaves(employeeId);
                break;
            case Constants.LeaveRequestStatus.APPROVED:
                leaves = employeeService.getApprovedLeaves(employeeId);
                break;
            default:
                leaves = new ArrayList<AnnualLeaveRequest>();
        }
        return leaves;
    }
    @ApiOperation(value = "List of all employees", response = List.class)
    @GetMapping("/findAll")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @ApiOperation(value = "Finds an employee by employeeId.")
    @GetMapping("/findById/{employeeId}")
    @ResponseBody
    public Employee findById(@PathVariable(required = true, name = "employeeId") long employeeId){
        return employeeService.findById(employeeId);
    }

    @ApiOperation(value = "Remaining days of employees annual leave rights")
    @GetMapping("/leave/remaining/{employeeId}")
    public long getRemainingAnnulLeaveDayCount(@PathVariable(required = true, name = "employeeId") long employeeId){
        long remainingDayCount = -1;
        remainingDayCount = employeeService.getRemainingAnnulLeaveDayCount(employeeId);
        return remainingDayCount;
    }

    @ApiOperation(value = "Create employee")
    @PostMapping("create")
    public Employee create(@ApiParam(name = "Example ", value = "Example json : \n{\n" +
            "  \"name\": \"Can Yılmaz\",\n" +
            "  \"startDate\": \"2019-10-26\"\n" +
            "}") @RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @ApiOperation(value = "Update employee")
    @PostMapping("update")
    public Employee update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }

    @ApiOperation(value = "Delete employee")
    @DeleteMapping("/delete/{employeeId}")
    public void deleteById(@PathVariable(required = true, name = "employeeId") long employeeId){
        employeeService.deleteById(employeeId);
    }
}
