package com.baizhi.dao;

import com.baizhi.entity.Department;
import com.baizhi.entity.Employee;

import java.util.List;

public interface DepartmentDao {
    public List<Department> findAll();
}
