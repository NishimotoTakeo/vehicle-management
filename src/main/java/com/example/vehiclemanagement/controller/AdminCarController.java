package com.example.vehiclemanagement.controller;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vehiclemanagement.entity.Car;
import com.example.vehiclemanagement.form.CarEditForm;
import com.example.vehiclemanagement.form.CarRegisterForm;
import com.example.vehiclemanagement.repository.CarRepository;
import com.example.vehiclemanagement.service.CarService;

@Controller
@RequestMapping("/admin/cars")
public class AdminCarController {
    private final CarRepository carRepository;
    private final CarService carService; 
    
    public AdminCarController(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }	
    
    @GetMapping    
    public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {	
        Page<Car> carPage;
        
        if (keyword != null && !keyword.isEmpty()) {
            carPage = carRepository.findByNameLike("%" + keyword + "%", pageable);                
        } else {
            carPage = carRepository.findAll(pageable);
        }  
                
        model.addAttribute("carPage", carPage);
        model.addAttribute("keyword", keyword);
        
        return "admin/cars/index";
    }  
    
    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        Car car = carRepository.getReferenceById(id);
        
        model.addAttribute("car", car);
        
        return "admin/cars/show";
    }   
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("carRegisterForm", new CarRegisterForm());
        return "admin/cars/register";
    }  
    
    @PostMapping("/create")
    public String create(@ModelAttribute @Validated CarRegisterForm carRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {        
        if (bindingResult.hasErrors()) {
            return "admin/cars/register";
        }
        
        carService.create(carRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage", "車両を登録しました。");    
        
        return "redirect:/admin/cars";
    }   
    
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
        Car car = carRepository.getReferenceById(id);
        String imageName = car.getImageName();
        CarEditForm carEditForm = new CarEditForm(car.getId(), car.getName(), null, car.getDescription(), car.getStaring(), car.getGoaling(), car.getCarModel(), car.getAddress(), car.getCarNumber());
        
        model.addAttribute("imageName", imageName);
        model.addAttribute("carEditForm", carEditForm);
        
        return "admin/cars/edit";
    }    
    
    @PostMapping("/{id}/update")
    public String update(@ModelAttribute @Validated CarEditForm carEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {        
        if (bindingResult.hasErrors()) {
            return "admin/cars/edit";
        }
        
        carService.update(carEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "車両情報を編集しました。");
        
        return "redirect:/admin/cars";
        
    }
    
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {        
        carRepository.deleteById(id);
                
        redirectAttributes.addFlashAttribute("successMessage", "車両を削除しました。");
        
        return "redirect:/admin/cars";
    }
}