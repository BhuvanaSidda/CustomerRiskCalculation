package com.proj.custriskcal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.custriskcal.entity.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, String> {

	Optional<CustomerDetails> findById(String accountKey);
}
