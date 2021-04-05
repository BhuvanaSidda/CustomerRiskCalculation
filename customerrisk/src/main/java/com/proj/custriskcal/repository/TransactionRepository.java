package com.proj.custriskcal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proj.custriskcal.entity.TransactionDetails;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails, String> {

	@Query(value = "select count(*) from transaction_details where transaction_origin_dest= ? and transaction_type = ? and DATE_FORMAT(transaction_date, '%Y-%m-%d') like ? ", nativeQuery = true)
	Integer getTransactionDetails(String country, String transType, String date);

	@Query(value = "select sum(transaction_amount) from transaction_details where transaction_origin_dest= ? and transaction_type = ? and DATE_FORMAT(transaction_date, '%Y-%m-%d') like ? ", nativeQuery = true)
	Double getTransactionAmount(String country, String transType, String date);
	
	@Query(value = "select count(transaction_date) as count  , transaction_date, transaction_origin_dest from transaction_details where DATE_FORMAT(transaction_date, '%Y-%m-%d') like ? group by transaction_date order by  transaction_origin_dest", nativeQuery = true)
	List<Object[]> getDayWiseCount(String monthYear);
	
	@Query(value = "select count(*) from transaction_details where account_key= ? and transaction_type = ? and DATE_FORMAT(transaction_date, '%Y-%m-%d') like ? ", nativeQuery = true)
	Integer getTransactionDetailsByAccount(String accountKey, String transType, String date);

	@Query(value = "select sum(transaction_amount) from transaction_details where account_key= ? and transaction_type = ? and DATE_FORMAT(transaction_date, '%Y-%m-%d') like ? ", nativeQuery = true)
	Double getTransactionAmountByaccount(String accountKey, String transType, String date);
	
	@Query(value = "select count(transaction_date) as count  , transaction_date, account_key from transaction_details  where DATE_FORMAT(transaction_date, '%Y-%m-%d') like ? group by transaction_date order by  account_key", nativeQuery = true)
	List<Object[]> getDayWiseCountByaccount(String monthYear);

	@Query(value = "select * from transaction_details where account_key = ? and DATE_FORMAT(transaction_date, '%Y-%m-%d') like ? ", nativeQuery = true)
	List<TransactionDetails> findByAccountKey(String accountKey, String monthYear);

	List<TransactionDetails> findByAccountKey(String accountKey);
}
