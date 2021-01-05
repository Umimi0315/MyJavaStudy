package com.atguigu.component;

import com.atguigu.bean.Employee;
import com.atguigu.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * 两个泛型
 * S：Source
 * T：Target
 *将S转为T
 */

public class MyStringToEmployeeConverter implements Converter<String, Employee> {

    @Autowired
    DepartmentDao departmentDao;

    /**
     * 自定义的转换规则
     * @param s
     * @return
     */
    @Override
    public Employee convert(String s) {
        System.out.println("页面提交的将要转换的字符串"+s);
        Employee employee = new Employee();
        if (s.contains("-")){
            String[] split = s.split("-");
            employee.setLastName(split[0]);
            employee.setEmail(split[1]);
            employee.setGender(Integer.parseInt(split[2]));
            employee.setDepartment(departmentDao.getDepartment(Integer.parseInt(split[3])));

        }
        return employee;
    }
}
