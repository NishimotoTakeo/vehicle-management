package com.example.vehiclemanagement.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarEditForm {
	 @NotNull
     private Integer id;    
     
     @NotBlank(message = "車両名を入力してください。")
     private String name;
         
     private MultipartFile imageFile;
     
     @NotBlank(message = "説明を入力してください。")
     private String description;   
     
     @NotNull(message = "乗車時メーター値を入力してください。")
     @Min(value = 1, message = "1km以上に設定してください。")
     private Integer staring; 
     
     @NotNull(message = "降車時メーター値を入力してください。")
     @Min(value = 1, message = "1km以上に設定してください。")
     private Integer goaling;       
     
     @NotBlank(message = "車種を入力してください。")
     private String carModel;
     
     @NotBlank(message = "所属を入力してください。")
     private String address;
     
     @NotBlank(message = "ナンバーを入力してください。")
     private String carNumber;

}
