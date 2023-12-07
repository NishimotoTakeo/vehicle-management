package com.example.vehiclemanagement.service;
 
 import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclemanagement.entity.Car;
import com.example.vehiclemanagement.form.CarEditForm;
import com.example.vehiclemanagement.form.CarRegisterForm;
import com.example.vehiclemanagement.repository.CarRepository;
 
 @Service
 public class CarService {
     private final CarRepository carRepository;    
     
     public CarService(CarRepository carRepository) {
         this.carRepository = carRepository;        
     }    
     
     @Transactional
     public void create(CarRegisterForm carRegisterForm) {
         Car car = new Car();        
         MultipartFile imageFile = carRegisterForm.getImageFile();
         
         if (!imageFile.isEmpty()) {
             String imageName = imageFile.getOriginalFilename(); 
             String hashedImageName = generateNewFileName(imageName);
             Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
             copyImageFile(imageFile, filePath);
             car.setImageName(hashedImageName);
         }
         
         car.setName(carRegisterForm.getName());                
         car.setDescription(carRegisterForm.getDescription());
         car.setStaring(carRegisterForm.getStaring());
         car.setGoaling(carRegisterForm.getGoaling());
         car.setCarModel(carRegisterForm.getCarModel());
         car.setAddress(carRegisterForm.getAddress());
         car.setCarNumber(carRegisterForm.getCarNumber());
                     
         carRepository.save(car);
     }
     
     @Transactional
     public void update(CarEditForm carEditForm) {
         Car car = carRepository.getReferenceById(carEditForm.getId());
         MultipartFile imageFile = carEditForm.getImageFile();
         
         if (!imageFile.isEmpty()) {
             String imageName = imageFile.getOriginalFilename(); 
             String hashedImageName = generateNewFileName(imageName);
             Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
             copyImageFile(imageFile, filePath);
             car.setImageName(hashedImageName);
         }
         
         car.setName(carEditForm.getName());                
         car.setDescription(carEditForm.getDescription());
         car.setStaring(carEditForm.getStaring());
         car.setGoaling(carEditForm.getGoaling());
         car.setCarModel(carEditForm.getCarModel());
         car.setAddress(carEditForm.getAddress());
         car.setCarNumber(carEditForm.getCarNumber());
                     
         carRepository.save(car);
     }    
     
     // UUIDを使って生成したファイル名を返す
     public String generateNewFileName(String fileName) {
         String[] fileNames = fileName.split("\\.");                
         for (int i = 0; i < fileNames.length - 1; i++) {
             fileNames[i] = UUID.randomUUID().toString();            
         }
         String hashedFileName = String.join(".", fileNames);
         return hashedFileName;
     }     
     
     // 画像ファイルを指定したファイルにコピーする
     public void copyImageFile(MultipartFile imageFile, Path filePath) {           
         try {
             Files.copy(imageFile.getInputStream(), filePath);
         } catch (IOException e) {
             e.printStackTrace();
         }          
     } 
 }