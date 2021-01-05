package com.baizhi.service;

import com.baizhi.dao.EmployeeDao;
import com.baizhi.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findEmpById(Integer id) {
        return employeeDao.findEmpById(id);
    }

    @Override
    public void deleteById(Integer id) {
        employeeDao.deleteById(id);
    }

    @Override
    public void saveEmp(Employee employee) {
        employeeDao.saveEmp(employee);
    }

    @Override
    public void updateEmp(Employee employee) {
        employeeDao.updateEmp(employee);
    }
}
