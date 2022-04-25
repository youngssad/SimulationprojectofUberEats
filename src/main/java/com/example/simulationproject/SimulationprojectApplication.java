package com.example.simulationproject;

import com.example.simulationproject.controller.EmployeeController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@Slf4j
@SpringBootApplication
@ServletComponentScan
public class SimulationprojectApplication {

    public static void main(String[] args) {

        SpringApplication.run(SimulationprojectApplication.class, args);

        log.info("项目成功");
    }

}
