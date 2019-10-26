package com.mcan.ykb.unitcase.controller;

import com.mcan.ykb.unitcase.model.AnnualLeaveAction;
import com.mcan.ykb.unitcase.service.IAnnualLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private IAnnualLeaveService annualLeaveService;

    @GetMapping("/findAll")
    public List<AnnualLeaveAction> findAll(){
        return annualLeaveService.findAll();
    }

}
