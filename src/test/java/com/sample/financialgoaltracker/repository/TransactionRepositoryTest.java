package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    private Transaction transaction1;
    private Transaction transaction2;

    @Before
    public void testSetUp(){
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
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(transaction1);
        expectedTransactions.add(transaction2);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        List<Transaction> actualTransactions = (List<Transaction>) transactionRepository.findAll();
        assertThat(actualTransactions).isEqualTo(expectedTransactions);
    }

    @Test
    public void testFindById(){
        Transaction expectedTransaction = transaction1;
        transactionRepository.save(expectedTransaction);
        Optional<Transaction> actualTransaction = transactionRepository.findById(expectedTransaction.getId());
        assertThat(actualTransaction.get()).isEqualTo(expectedTransaction);
    }

    @Test
    public void testSave(){
        Transaction expectedTransaction = transaction1;
        transactionRepository.save(expectedTransaction);
        Optional<Transaction> actualTransaction = transactionRepository.findById(expectedTransaction.getId());
        assertThat(actualTransaction.get()).isEqualTo(expectedTransaction);
     }

     @Test
    public void testDelete(){
        Transaction transaction = transaction1;
        transactionRepository.save(transaction);
        Optional<Transaction> actualTransaction = transactionRepository.findById(transaction.getId());
        assertThat(actualTransaction.get()).isEqualTo(transaction);
        transactionRepository.delete(transaction);
        Optional<Transaction> returnedTransaction = transactionRepository.findById(transaction.getId());
        assertThat(returnedTransaction).isEqualTo(Optional.empty());
     }
}




















