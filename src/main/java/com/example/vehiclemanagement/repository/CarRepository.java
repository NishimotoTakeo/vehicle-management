package com.example.vehiclemanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehiclemanagement.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	public Page<Car> findByNameLike(String keyword, Pageable pageable);
	
	 public Page<Car> findByNameLikeOrAddressLikeOrderByCreatedAtDesc(String nameKeyword, String addressKeyword, Pageable pageable);  
	 public Page<Car> findByNameLikeOrAddressLikeOrderByStaringAsc(String nameKeyword, String addressKeyword, Pageable pageable);  
	 public Page<Car> findByAddressLikeOrderByCreatedAtDesc(String area, Pageable pageable);
	 public Page<Car> findByAddressLikeOrderByStaringAsc(String area, Pageable pageable);
	 public Page<Car> findByStaringLessThanEqualOrderByCreatedAtDesc(Integer staring, Pageable pageable);
	 public Page<Car> findByStaringLessThanEqualOrderByStaringAsc(Integer staring, Pageable pageable); 
	 public Page<Car> findAllByOrderByCreatedAtDesc(Pageable pageable);
	 public Page<Car> findAllByOrderByStaringAsc(Pageable pageable); 
	    
	 public List<Car> findTop10ByOrderByCreatedAtDesc();

}