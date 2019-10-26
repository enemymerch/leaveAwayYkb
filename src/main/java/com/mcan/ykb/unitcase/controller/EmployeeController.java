package com.mcan.ykb.unitcase.controller;

import com.mcan.ykb.unitcase.model.Employee;
import com.mcan.ykb.unitcase.service.IAnnualLeaveService;
import com.mcan.ykb.unitcase.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IAnnualLeaveService annualLeaveService;

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/findAll")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public Employee findById(@PathVariable(required = true, name = "id") long id){
        return employeeService.findById(id);
    }

    @PostMapping("create")
    public Employee create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @PostMapping("update")
    public Employee update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable(required = true, name = "id") long id){
        employeeService.deleteById(id);
    }
}
