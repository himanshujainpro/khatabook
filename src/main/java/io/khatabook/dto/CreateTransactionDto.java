package io.khatabook.dto;

import io.khatabook.enums.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTransactionDto {
    private String details_text;

    private LocalDateTime transaction_date = LocalDateTime.now();

    private TransactionType transaction_type;

    private long transaction_amount=0;

    private long customer_id;
}
