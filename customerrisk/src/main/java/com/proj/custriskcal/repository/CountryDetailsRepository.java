package com.proj.custriskcal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proj.custriskcal.entity.CountryDetails;

@Repository
public interface CountryDetailsRepository extends JpaRepository<CountryDetails, Integer> {

	

}
