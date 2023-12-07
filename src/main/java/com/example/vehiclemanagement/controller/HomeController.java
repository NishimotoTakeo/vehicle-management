package com.example.vehiclemanagement.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.vehiclemanagement.entity.Car;
import com.example.vehiclemanagement.repository.CarRepository;

@Controller
public class HomeController {
	private final CarRepository carRepository;        
    
    public HomeController(CarRepository carRepository) {
        this.carRepository = carRepository;            
    }
	
	 @GetMapping("/")
	 public String index(Model model) {
         List<Car> newCars = carRepository.findTop10ByOrderByCreatedAtDesc();
         model.addAttribute("newCars", newCars); 
         
         return "index";
     }   
}
