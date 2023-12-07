package com.example.vehiclemanagement.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	 @NotBlank(message = "乗車日と降車日を選択してください。")
     private String fromCheckinDateToCheckoutDate;    
     
     @NotNull(message = "乗車時メーター値を入力してください。")
     @Min(value = 1, message = "宿泊人数は1人以上に設定してください。")
     private Integer staring;
     
     @NotNull(message = "降車時メーター値を入力してください。")
     @Min(value = 1, message = "宿泊人数は1人以上に設定してください。")
     private Integer goaling;
 
     // 乗車日を取得する
     public LocalDate getCheckinDate() {
         String[] checkinDateAndCheckoutDate = getFromCheckinDateToCheckoutDate().split(" から ");
         return LocalDate.parse(checkinDateAndCheckoutDate[0]);
     }
 
     // 降車日を取得する
     public LocalDate getCheckoutDate() {
         String[] checkinDateAndCheckoutDate = getFromCheckinDateToCheckoutDate().split(" から ");
         return LocalDate.parse(checkinDateAndCheckoutDate[0]);
     }

}
