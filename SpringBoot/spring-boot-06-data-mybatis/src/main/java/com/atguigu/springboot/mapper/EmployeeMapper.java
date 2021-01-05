package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Employee;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@Mapper或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
