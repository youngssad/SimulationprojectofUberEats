package com.example.simulationproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.simulationproject.Service.EmployeeService;
import com.example.simulationproject.common.R;
import com.example.simulationproject.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //employee login
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //get the password which was sent from client
        String password = employee.getPassword();
        //Encryption the password
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //search data base with username
        LambdaQueryWrapper<Employee> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername()   );
        Employee emp = employeeService.getOne(queryWrapper);
        //check if we have the data in the database
        if(emp==null){
            return R.error("Login fail");
        }
        //compare the password in the database with the password which was sent from client
        if(!emp.getPassword().equals(password)){
            return R.error("Login fail");
        }
        //if the password is correct, check employee status
        if(emp.getStatus()==0){
            return R.error("Your account is locked");
        }
        //if the password is correct and the status is 1, put employee information into session, return success
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);

    }

    //employee logout
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //clear the employee stored in the session
        request.getSession().removeAttribute("employee");
        return R.success("logout successfully");

    }

    //add new employee
    @PostMapping
    public R<String> save(@RequestBody Employee employee,HttpServletRequest request){

        //when add new employee, provide a default password, create time, update time
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//
        //get current login user id
//        Long employee1 = (Long) request.getSession().getAttribute("employee");
//
//        employee.setCreateUser(employee1);
//        employee.setUpdateUser(employee1);


        employeeService.save(employee);

        return R.success("new employee add successfully");

    }

    //page query
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //pagination constructor
        Page pageinfo= new Page(page,pageSize);
        //condition constructor
        LambdaQueryWrapper<Employee> queryWrapper= new LambdaQueryWrapper();

        //add filter condition

        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);

        //add sort condition
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //execute query
        employeeService.page(pageinfo,queryWrapper);

        return R.success(pageinfo);


    }

    //update user info with ID
    @PutMapping
    public R<String> update(@RequestBody Employee employee, HttpServletRequest request){
        Long empID = (Long) request.getSession().getAttribute("employee");

        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empID);
        employeeService.updateById(employee);

        return R.success("Employee info update successfully");

    }

    //find employee info by Id
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee byId = employeeService.getById(id);
        if(byId!=null) {
            return R.success(byId);
        }
        return R.error("can't find employee");
    }


}
