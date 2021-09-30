package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
