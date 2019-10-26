package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee findById(long id);
    Employee create(Employee employee);
    Employee update(Employee employee);
    void deleteById (long id);
}
