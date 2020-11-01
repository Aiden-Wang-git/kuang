package com.lemon.study.controller;

import com.lemon.study.dao.DepartmentDao;
import com.lemon.study.dao.EmployeeDao;
import com.lemon.study.pojo.Department;
import com.lemon.study.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * @description:
 * @author: WangJun
 * @time: 2020/11/1 13:56
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    //进入员工列表页面
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //进入新增员工页面
    @GetMapping("/add")
    public String add(Model  model){
        model.addAttribute("departments",departmentDao.getDepartments());
        return "emp/add";
    }

    //新增员工
    @PostMapping("/add")
    public String addEmp(Employee employee){
        System.out.println("employee=>"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //进入编辑员工页面
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id){
        Employee empByID = employeeDao.getEmployee(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        model.addAttribute("empByID",empByID);
        return "emp/edit";
    }
    //编辑员工
    @PostMapping("/edit")
    public String editEmp(Employee employee){
        System.out.println("employee=>"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除员工
}
