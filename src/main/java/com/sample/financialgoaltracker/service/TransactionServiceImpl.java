package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.TransactionDTO;
import com.sample.financialgoaltracker.entity.Transaction;
import com.sample.financialgoaltracker.mapper.TransactionMapper;
import com.sample.financialgoaltracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public List<TransactionDTO> findAll(){
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();

        for(Transaction transaction : transactions){
            TransactionDTO transactionDTO = transactionMapper.convertToDTO(transaction);
            transactionDTOS.add(transactionDTO);
        }
        return transactionDTOS;
    }

    @Override
    public TransactionDTO findById(int id){
        return transactionMapper.convertToDTO(transactionRepository.findById(id).get());
    }

    @Override
    public TransactionDTO save(TransactionDTO theTransactionDTO){
        Transaction transaction = transactionMapper.convertToEntity(theTransactionDTO);
        transactionRepository.save(transaction);
        TransactionDTO transactionDTO = transactionMapper.convertToDTO(transaction);
        return transactionDTO;
    }

    @Override
    public boolean deleteById(int id) {
        transactionRepository.deleteById(id);
        return true;
    }
}
