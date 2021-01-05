package com.baizhi;

import com.baizhi.entity.Employee;
import com.baizhi.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestEmployeeService {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testFindAll(){
        List<Employee> list = employeeService.findAll();
        for (Employee employee : list) {
            System.out.println("employee="+employee);
        }

        System.out.println("=====================");

        list = employeeService.findAll();
        for (Employee employee : list) {
            System.out.println("employee="+employee);
        }

    }
    @Test
    public void testFindOne(){
        employeeService.findEmpById(1);
        System.out.println("==============");
        employeeService.findEmpById(1);
    }

    @Test
    public void testDeleteEmpById(){
        employeeService.deleteById(4);
    }

    @Test
    public void testsaveEmp(){
        Employee employee = new Employee(null,"baizhi", "baizhi@baizhi.com", 1, 1);
        employeeService.saveEmp(employee);
    }

    @Test
    public void testUpdateEmp(){
        Employee employee = new Employee(3,"baizhi", "abc123@atguigu.com", 0, 0);
        employeeService.updateEmp(employee);
    }
}
