package com.baizhi.dao;

import com.baizhi.entity.Employee;


import java.util.List;

public interface EmployeeDao {

    public List<Employee> findAll();

    public Employee findEmpById(Integer id);

    public void deleteById(Integer id);

    public void saveEmp(Employee employee);

    public void updateEmp(Employee employee);

}
