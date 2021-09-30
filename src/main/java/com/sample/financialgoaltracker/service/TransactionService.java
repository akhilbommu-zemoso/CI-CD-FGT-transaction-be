package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> findAll();

    TransactionDTO findById(int id);

    TransactionDTO save(TransactionDTO transactionDTO);

    boolean deleteById(int id);
}
