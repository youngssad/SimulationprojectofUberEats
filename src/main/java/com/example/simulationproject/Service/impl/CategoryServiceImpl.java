package com.example.simulationproject.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simulationproject.Service.CategoryService;
import com.example.simulationproject.Service.DishService;
import com.example.simulationproject.Service.SetMealService;
import com.example.simulationproject.common.CustomException;
import com.example.simulationproject.entity.Category;
import com.example.simulationproject.entity.Dish;
import com.example.simulationproject.entity.Setmeal;
import com.example.simulationproject.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetMealService setMealService;
    //delete category by Id
    //check if the category is connected with other meal or combo

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> queryWrapper= new LambdaQueryWrapper<>();
        //add query condition, query with category Id
        queryWrapper.eq(Dish::getCategoryId,id);
        long count = dishService.count(queryWrapper);
        //if the category is connected with meal, we can't delete it
        if(count>0){
            //throw exception
            throw new CustomException("Category connected with dish, can't delete");
        }



        LambdaQueryWrapper<Setmeal> queryWrapper1= new LambdaQueryWrapper<>();
        //add query condition, query with category Id
        queryWrapper1.eq(Setmeal::getCategoryId,id);
        long count1 = setMealService.count(queryWrapper1);
        //if the category is connected with setmeal, we can't delete it
        if(count1>0){
            //throw exception
            throw new CustomException("Category connected with setmeal, can't delete");
        }

        super.removeById(id);

    }

}