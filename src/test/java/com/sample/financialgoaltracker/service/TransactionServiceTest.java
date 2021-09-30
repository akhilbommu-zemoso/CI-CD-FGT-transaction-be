package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.TransactionDTO;
import com.sample.financialgoaltracker.entity.User;
import com.sample.financialgoaltracker.entity.Transaction;
import com.sample.financialgoaltracker.mapper.TransactionMapper;
import com.sample.financialgoaltracker.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Autowired
    private TransactionMapper transactionMapper;

    private Transaction transaction1;
    private Transaction transaction2;

    @Before
    public void setUp(){
        transaction1 = new Transaction();
        transaction1.setCategory("sales");
        transaction1.setType("receipts");
        transaction1.setDate("2020-05-11");
        transaction1.setFrequency("weekly");
        transaction1.setCreatedAt("12:23");
        transaction1.setCreatedBy("ray");
        transaction1.setModifiedAt("13:23");
        transaction1.setModifiedBy("ray");
        transaction1.setDeleted(false);
        transaction1.setActive(false);

        transaction2 = new Transaction();
        transaction2.setCategory("sales");
        transaction2.setType("receipts");
        transaction2.setDate("2020-04-11");
        transaction2.setFrequency("weekly");
        transaction2.setCreatedAt("13:23");
        transaction2.setCreatedBy("ray");
        transaction2.setModifiedAt("15:23");
        transaction2.setModifiedBy("bruce");
        transaction2.setDeleted(false);
        transaction2.setActive(false);

    }

    @Test
    public void testFindAll(){
        List<Transaction> transactionsList = new ArrayList<>();

        transactionsList.add(transaction1);
        transactionsList.add(transaction2);

        //Mocking
        Mockito.when(transactionRepository.findAll()).thenReturn(transactionsList);

        //Test
        List<TransactionDTO> totalTransactions = transactionServiceImpl.findAll();
        assertThat(2).isEqualTo(totalTransactions.size());
    }

    @Test
    public void testFindById(){
        Mockito.when(transactionRepository.findById(transaction1.getId())).
                thenReturn(Optional.of(transaction1));
        TransactionDTO transactionDTO = transactionServiceImpl.findById(transaction1.getId());
        assertThat(transaction1).isEqualToComparingFieldByField(transactionMapper.convertToEntity(transactionDTO));
    }

    @Test
    public void testSave(){
        Mockito.when(transactionRepository.save(transaction1)).thenReturn(transaction1);
        assertThat(transactionMapper.convertToDTO(transaction1)).isEqualToComparingFieldByField(transactionServiceImpl.save(transactionMapper.convertToDTO(transaction1)));
    }

    @Test
    public void testDeleteById(){
        transactionServiceImpl.deleteById(transaction1.getId());
        Mockito.verify(transactionRepository,Mockito.times(1))
                .deleteById(transaction1.getId());
    }
}














