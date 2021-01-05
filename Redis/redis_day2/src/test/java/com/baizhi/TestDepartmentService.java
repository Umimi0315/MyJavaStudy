package com.baizhi;

import com.baizhi.entity.Department;
import com.baizhi.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDepartmentService {
    @Autowired
    DepartmentService departmentService;

    @Test
    public void testFindAll(){
        List<Department> departments = departmentService.findAll();
        for (Department department : departments) {
            System.out.println(department);
        }
        System.out.println("================");
        departments = departmentService.findAll();
        for (Department department : departments) {
            System.out.println(department);
        }
    }
}
