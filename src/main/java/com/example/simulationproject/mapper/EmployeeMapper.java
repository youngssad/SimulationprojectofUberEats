package com.example.simulationproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simulationproject.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
