package com.example.vehiclemanagement.repository;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehiclemanagement.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	//public Page<Reservation> findByUserOrderByCreatedAtDesc(Pageable pageable);
}
