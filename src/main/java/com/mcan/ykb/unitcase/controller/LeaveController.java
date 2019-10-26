package com.mcan.ykb.unitcase.controller;

import com.mcan.ykb.unitcase.exception.LeaveRequestException;
import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;
import com.mcan.ykb.unitcase.service.IAnnualLeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Leave Handler", description = "Leave CRUD and listing endpoints")
@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private IAnnualLeaveService annualLeaveService;

    @ApiOperation(value = "Approve leave request")
    @PostMapping("/approve/{requestId}")
    public AnnualLeaveRequest approveRequest(@PathVariable(required = true, name = "requestId") long requestId){
        return annualLeaveService.approve(requestId);
    }

    @ApiOperation(value = "Rejects leave request")
    @PostMapping("/reject/{requestId}")
    public AnnualLeaveRequest rejectRequest(@PathVariable(required = true, name = "requestId") long requestId){
        return annualLeaveService.reject(requestId);
    }

    @ApiOperation(value = "List of all employees", response = List.class)
    @GetMapping("/findAll")
    public List<AnnualLeaveRequest> findAll(){
        return annualLeaveService.findAll();
    }

    @ApiOperation(value = "Finds an leave request by requestId.")
    @GetMapping("/findById/{requestId}")
    @ResponseBody
    public AnnualLeaveRequest findById(@PathVariable(required = true, name = "requestId") long requestId){
        return annualLeaveService.findById(requestId);
    }

    @ApiOperation(value = "Create leave request")
    @PostMapping("create")
    public AnnualLeaveRequest create(@ApiParam(name = "example", value = "\n{\n" +
            "\t\"leaveStartDate\":\"2019-10-28\",\n" +
            "\t\"leaveEndDate\":\"2019-11-10\",\n" +
            "\t\"employee\" : {\n" +
            "\t\t\"id\" : 1\n" +
            "\t}\n" +
            "\t\n" +
            "}") @RequestBody AnnualLeaveRequest annualLeaveRequest) throws LeaveRequestException {
        return annualLeaveService.create(annualLeaveRequest);
    }

    @ApiOperation(value = "Update leave request")
    @PostMapping("update")
    public AnnualLeaveRequest update(@RequestBody AnnualLeaveRequest annualLeaveRequest){
        return annualLeaveService.update(annualLeaveRequest);
    }

    @ApiOperation(value = "Delete leave request")
    @DeleteMapping("/delete/{requestId}")
    public void deleteById(@PathVariable(required = true, name = "requestId") long requestId){
        annualLeaveService.deleteById(requestId);
    }
}
