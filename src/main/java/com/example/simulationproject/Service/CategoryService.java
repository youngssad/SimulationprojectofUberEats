package com.example.simulationproject.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simulationproject.entity.Category;

public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
