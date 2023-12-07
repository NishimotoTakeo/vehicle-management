package com.example.vehiclemanagement.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vehiclemanagement.entity.Car;
import com.example.vehiclemanagement.form.ReservationInputForm;
import com.example.vehiclemanagement.form.ReservationRegisterForm;
import com.example.vehiclemanagement.repository.CarRepository;
import com.example.vehiclemanagement.repository.ReservationRepository;
import com.example.vehiclemanagement.service.ReservationService;

@Controller
public class ReservationController {
// private final ReservationRepository reservationRepository;    
 private final CarRepository carRepository;
 private final ReservationService reservationService; 
     
 public ReservationController(ReservationRepository reservationRepository, CarRepository carRepository, ReservationService reservationService) {          
  //       this.reservationRepository = reservationRepository;  
         this.carRepository = carRepository;
         this.reservationService = reservationService;
     }    
 
     @GetMapping("/reservations")
     public String index(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
              
         
         return "reservations/index";
     }
     
     @GetMapping("/cars/{id}/reservations/input")
     public String input(@PathVariable(name = "id") Integer id,
                         @ModelAttribute @Validated ReservationInputForm reservationInputForm,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model)
     {   
         Car car = carRepository.getReferenceById(id);
    //     Integer staring = reservationInputForm.getStaring();   
    //     Integer goaling = car.getGoaling();
         
        
         
         if (bindingResult.hasErrors()) {            
             model.addAttribute("car", car);            
             model.addAttribute("errorMessage", "更新内容に不備があります。"); 
             return "cars/show";
         }
         
         redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);           
         
         return "redirect:/cars/{id}/reservations/confirm";
     }    

     @GetMapping("/cars/{id}/reservations/confirm")
     public String confirm(@PathVariable(name = "id") Integer id,
                           @ModelAttribute ReservationInputForm reservationInputForm,
                           Model model) 
     {        
         Car car = carRepository.getReferenceById(id);
          
                 
         //乗車日と降車日を取得する
         LocalDate checkinDate = reservationInputForm.getCheckinDate();
         LocalDate checkoutDate = reservationInputForm.getCheckoutDate();
  
         
         ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(car.getId(), checkinDate.toString(), checkoutDate.toString(), reservationInputForm.getStaring(),reservationInputForm.getGoaling() );
         
         model.addAttribute("car", car);  
         model.addAttribute("reservationRegisterForm",reservationRegisterForm);       
         
         return "reservations/confirm";
     }    
     
     @PostMapping("/cars/{id}/reservations/create")
     public String create(@ModelAttribute ReservationRegisterForm reservationRegisterForm) {                
         reservationService.create(reservationRegisterForm);
         
         return "redirect:/reservations?reserved";
     }
     
}
