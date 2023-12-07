package com.example.vehiclemanagement.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehiclemanagement.entity.Car;
import com.example.vehiclemanagement.entity.Reservation;
import com.example.vehiclemanagement.form.ReservationRegisterForm;
import com.example.vehiclemanagement.repository.CarRepository;
import com.example.vehiclemanagement.repository.ReservationRepository;

@Service
public class ReservationService {
	 private final ReservationRepository reservationRepository;  
     private final CarRepository carRepository;  
     
     public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository) {
         this.reservationRepository = reservationRepository;  
         this.carRepository = carRepository;  
     }    
     
     @Transactional
     public void create(ReservationRegisterForm reservationRegisterForm) { 
         Reservation reservation = new Reservation();
         Car car = carRepository.getReferenceById(reservationRegisterForm.getCarId());
         LocalDate checkinDate = LocalDate.parse(reservationRegisterForm.getCheckinDate());
         LocalDate checkoutDate = LocalDate.parse(reservationRegisterForm.getCheckoutDate());         
                 
         reservation.setCar(car);
         reservation.setCheckinDate(checkinDate);
         reservation.setCheckoutDate(checkoutDate);
         reservation.setStaring(reservationRegisterForm.getStaring());
         reservation.setGoaling(reservationRegisterForm.getGoaling());
         
         reservationRepository.save(reservation);
     }    
}
