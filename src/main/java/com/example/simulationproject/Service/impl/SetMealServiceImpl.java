package com.example.simulationproject.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simulationproject.Service.SetMealService;
import com.example.simulationproject.entity.Setmeal;
import com.example.simulationproject.mapper.SetMealMapper;
import org.springframework.stereotype.Service;

@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetMealService {
}
