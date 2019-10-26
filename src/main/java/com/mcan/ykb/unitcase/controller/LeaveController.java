package com.mcan.ykb.unitcase.controller;

import com.mcan.ykb.unitcase.model.AnnualLeaveRequest;
import com.mcan.ykb.unitcase.service.IAnnualLeaveService;
import com.mcan.ykb.unitcase.utils.Constants;
import com.mcan.ykb.unitcase.service.LeaveRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private IAnnualLeaveService annualLeaveService;

    @GetMapping("/remaining/{id}")
    public String remainig(@PathVariable(required = true, name = "id") long id) {
        return annualLeaveService.getRemainingLeaveDay(id);
    }

    @PostMapping("/approve/{id}")
    public AnnualLeaveRequest approveRequest(@PathVariable(required = true, name = "id") long id){
        return annualLeaveService.approve(id);
    }

    @PostMapping("/reject/{id}")
    public AnnualLeaveRequest rejectRequest(@PathVariable(required = true, name = "id") long id){
        return annualLeaveService.reject(id);
    }

    @GetMapping("/findAll")
    public List<AnnualLeaveRequest> findAll(){
        return annualLeaveService.findAll();
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public AnnualLeaveRequest findById(@PathVariable(required = true, name = "id") long id){
        return annualLeaveService.findById(id);
    }

    @PostMapping("create")
    public AnnualLeaveRequest create(@RequestBody AnnualLeaveRequest annualLeaveRequest) throws LeaveRequestException {
        return annualLeaveService.create(annualLeaveRequest);
    }

    @PostMapping("update")
    public AnnualLeaveRequest update(@RequestBody AnnualLeaveRequest annualLeaveRequest){
        return annualLeaveService.update(annualLeaveRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(required = true, name = "id") long id){
        annualLeaveService.deleteById(id);
    }
}
