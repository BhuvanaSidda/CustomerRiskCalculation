package com.proj.custriskcal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.custriskcal.entity.CustomerAccountDetails;

@Repository
public interface CustomerAccountsRepository extends JpaRepository<CustomerAccountDetails, String> {

	Optional<CustomerAccountDetails> findById(String accountKey);

}
