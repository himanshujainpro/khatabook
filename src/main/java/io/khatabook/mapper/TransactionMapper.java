package io.khatabook.mapper;


import io.khatabook.dto.CreateTransactionDto;
import io.khatabook.model.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {
    Transaction dtoToTransaction(CreateTransactionDto createTransactionDto);
}
