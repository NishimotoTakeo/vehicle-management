package com.example.vehiclemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehiclemanagement.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
