package com.example.simulationproject.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simulationproject.Service.DishService;
import com.example.simulationproject.entity.Dish;
import com.example.simulationproject.mapper.DishMapper;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
