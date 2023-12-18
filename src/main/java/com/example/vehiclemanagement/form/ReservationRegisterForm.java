package com.example.vehiclemanagement.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
	
	 //private Integer id;
	
	 private Integer carId;
      
	 private String checkinDate;    
     
     private String checkoutDate;
         
     private Integer staring;    
         
     private Integer goaling;    
     
       
}
