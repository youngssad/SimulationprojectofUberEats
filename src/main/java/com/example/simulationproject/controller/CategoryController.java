package com.example.simulationproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.simulationproject.Service.CategoryService;
import com.example.simulationproject.common.R;
import com.example.simulationproject.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody Category category){

        categoryService.save(category);
        return R.success("save successfully");
    }

    //page query
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        //pagination constructor
        Page<Category> pageInfo= new Page<>();

        //condition constructor
        LambdaQueryWrapper<Category> queryWrapper= new LambdaQueryWrapper<>();

        queryWrapper.orderByAsc(Category::getSort);

        //execute query
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    //delete category
    @DeleteMapping
    public R<String> delete(Long ids){

        categoryService.remove(ids);

        return R.success("delete category successfully");
    }

    //update Category
    @PutMapping
    public R<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("category update successfully");
    }
}
