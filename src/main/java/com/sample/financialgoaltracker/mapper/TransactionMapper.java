package com.sample.financialgoaltracker.mapper;

import com.sample.financialgoaltracker.dto.TransactionDTO;
import com.sample.financialgoaltracker.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Transaction convertToEntity(TransactionDTO transactionDTO){
        return modelMapper.map(transactionDTO, Transaction.class);
    }

    public TransactionDTO convertToDTO(Transaction transaction){
        return modelMapper.map(transaction, TransactionDTO.class);
    }
}
