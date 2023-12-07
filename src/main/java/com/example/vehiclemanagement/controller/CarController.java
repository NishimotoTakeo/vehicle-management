package com.example.vehiclemanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.vehiclemanagement.entity.Car;
import com.example.vehiclemanagement.form.ReservationInputForm;
import com.example.vehiclemanagement.repository.CarRepository;

@Controller
@RequestMapping("/cars")
public class CarController{
 private final CarRepository carRepository;        
     
     public CarController(CarRepository carRepository) {
         this.carRepository = carRepository;            
     }     
   
     @GetMapping
     public String index(@RequestParam(name = "keyword", required = false) String keyword,
                         @RequestParam(name = "area", required = false) String area,
                         @RequestParam(name = "staring", required = false) Integer staring,
                         @RequestParam(name = "order", required = false) String order,
                         @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
                         Model model) 
     {
         Page<Car> carPage;
                 
         if (keyword != null && !keyword.isEmpty()) {
        	
        	 
        	 if (order != null && order.equals("staringAsc")) {
        	     carPage = carRepository.findByNameLikeOrAddressLikeOrderByStaringAsc("%" + keyword + "%", "%" + keyword + "%", pageable);
             } else {
                 carPage = carRepository.findByNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%", "%" + keyword + "%", pageable);
             }
             
             
         } else if (area != null && !area.isEmpty()) {
        	 if (order != null && order.equals("staringAsc")) {
                 carPage = carRepository.findByAddressLikeOrderByStaringAsc("%" + area + "%", pageable);
             } else {
                 carPage = carRepository.findByAddressLikeOrderByCreatedAtDesc("%" + area + "%", pageable);
             } 
         
         } else if (staring != null) {
        	 if (order != null && order.equals("staringAsc")) {
                 carPage = carRepository.findByStaringLessThanEqualOrderByStaringAsc(staring, pageable);
             } else {
                 carPage = carRepository.findByStaringLessThanEqualOrderByCreatedAtDesc(staring, pageable);
             }
              
         } else {
        	 if (order != null && order.equals("staringAsc")) {
                 carPage = carRepository.findAllByOrderByStaringAsc(pageable);
             } else {
                 carPage = carRepository.findAllByOrderByCreatedAtDesc(pageable);   
             }
             
         }                
         
         model.addAttribute("carPage", carPage);
         model.addAttribute("keyword", keyword);
         model.addAttribute("area", area);
         model.addAttribute("staring", staring);                              
         
         return "cars/index";
     }
     
     @GetMapping("/{id}")
     public String show(@PathVariable(name = "id") Integer id, Model model) {
         Car car = carRepository.getReferenceById(id);
         
         model.addAttribute("car", car);    
         model.addAttribute("reservationInputForm", new ReservationInputForm());

         
         return "cars/show";
     }  

}
