package io.khatabook.repository;

import io.khatabook.dto.TransactionDto;
import io.khatabook.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select new io.khatabook.dto.TransactionDto(t.transaction_id,t.transaction_date,t.details_text,t.transaction_type,t.transaction_amount) from Transaction  t where t.customer.customer_id=?1")
    List<TransactionDto> findTransactionsByCustomer_Customer_id(long id);


}