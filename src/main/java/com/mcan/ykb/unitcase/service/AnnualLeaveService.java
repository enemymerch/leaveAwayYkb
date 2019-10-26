package com.mcan.ykb.unitcase.service;

import com.mcan.ykb.unitcase.dao.IGenericDao;
import com.mcan.ykb.unitcase.model.AnnualLeaveAction;
import com.mcan.ykb.unitcase.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnualLeaveService implements IAnnualLeaveService{

    private IGenericDao<Employee> employeeDao;
    private IGenericDao<AnnualLeaveAction> actionDao;


    @Override
    public List<AnnualLeaveAction> findAll() {
        return actionDao.findAll();
    }

    @Override
    public AnnualLeaveAction findById(long id) {
        return actionDao.findOne(id);
    }

    @Override
    public AnnualLeaveAction create(AnnualLeaveAction action) {
        try {
            actionDao.save(action);
        } catch (Exception e) {
            return null;
        }
        return action;
    }

    @Override
    public AnnualLeaveAction update(AnnualLeaveAction action) {
        try {
            actionDao.update(action);
        }catch (Exception e) {
            return null;
        }
        return action;
    }

    @Override
    public void deleteById(long id) {
        actionDao.deleteById(id);
    }

    @SuppressWarnings("unused")
    @Autowired
    public void setActionDao(IGenericDao<AnnualLeaveAction> actionDao) {
        this.actionDao = actionDao;
        this.actionDao.setClazz(AnnualLeaveAction.class);
    }

    @SuppressWarnings("unused")
    @Autowired
    public void setEmployeeDao(IGenericDao<Employee> employeeDao){
        this.employeeDao = employeeDao;
        this.employeeDao.setClazz(Employee.class);
    }
}
