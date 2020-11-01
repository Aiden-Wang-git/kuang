package com.lemon.study.dao;

import com.lemon.study.pojo.Department;
import com.lemon.study.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.crypto.interfaces.PBEKey;
import java.util.*;

/**
 * @description:
 * @author: WangJun
 * @time: 2020/10/30 15:23
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<>(); //创建一个部门
        employees.put(101,new Employee(101,"AA","A123@163.com",1,new Department(101,"A小卖部"),new Date()));
        employees.put(102,new Employee(102,"BB","B123@163.com",0,new Department(102,"B小卖部"),new Date()));
        employees.put(103,new Employee(103,"CC","C123@163.com",1,new Department(103,"C小卖部"),new Date()));
        employees.put(104,new Employee(104,"DD","D123@163.com",0,new Department(104,"D小卖部"),new Date()));
        employees.put(105,new Employee(105,"EE","E123@163.com",1,new Department(105,"E小卖部"),new Date()));
        employees.put(106,new Employee(106,"FF","F123@163.com",0,new Department(106,"F小卖部"),new Date()));
    }

    private static Integer initID = 106;
    //增加员工
    public void save(Employee employee){
        if (employee.getId() == null){
            employee.setId(initID++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getId()));
        employees.put(employee.getId(),employee);
    }

    //查询全部员工
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployee(Integer id){
        return employees.get(id);
    }

    //删除员工
    public void delete(Integer id){
        employees.remove(id);
    }

}
