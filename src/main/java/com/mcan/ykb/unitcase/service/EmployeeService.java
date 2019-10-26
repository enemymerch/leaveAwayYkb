package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.dao.IGenericDao;
import com.mcan.ykb.unitcase.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private IGenericDao<Employee> employeeDao;

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
    public Employee findById(long id) {
        return employeeDao.findOne(id);
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
    public void deleteById(long id) {
        employeeDao.deleteById(id);
    }
}
