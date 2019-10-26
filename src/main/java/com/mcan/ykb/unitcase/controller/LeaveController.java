package com.mcan.ykb.unitcase.controller;

import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;
import com.mcan.ykb.unitcase.service.IAnnualLeaveService;
import com.mcan.ykb.unitcase.exception.LeaveRequestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @PostMapping("/approve/{id}")
    public AnnualLeaveRequest approveRequest(@PathVariable(required = true, name = "id") long id){
        return annualLeaveService.approve(id);
    }

    @ApiOperation(value = "Rejects leave request")
    @PostMapping("/reject/{id}")
    public AnnualLeaveRequest rejectRequest(@PathVariable(required = true, name = "id") long id){
        return annualLeaveService.reject(id);
    }

    @ApiOperation(value = "List of all employees", response = List.class)
    @GetMapping("/findAll")
    public List<AnnualLeaveRequest> findAll(){
        return annualLeaveService.findAll();
    }

    @ApiOperation(value = "Finds an leave request by requestId.")
    @GetMapping("/findById/{id}")
    @ResponseBody
    public AnnualLeaveRequest findById(@PathVariable(required = true, name = "id") long id){
        return annualLeaveService.findById(id);
    }

    @ApiOperation(value = "Create leave request")
    @PostMapping("create")
    public AnnualLeaveRequest create(@RequestBody AnnualLeaveRequest annualLeaveRequest) throws LeaveRequestException {
        return annualLeaveService.create(annualLeaveRequest);
    }

    @ApiOperation(value = "Update leave request")
    @PostMapping("update")
    public AnnualLeaveRequest update(@RequestBody AnnualLeaveRequest annualLeaveRequest){
        return annualLeaveService.update(annualLeaveRequest);
    }

    @ApiOperation(value = "Delete leave request")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(required = true, name = "id") long id){
        annualLeaveService.deleteById(id);
    }
}
