package io.khatabook.dto;

import io.khatabook.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionDto {
    private long transaction_id;

    private LocalDateTime transaction_date;

    private String details_text;

    private TransactionType transaction_type;

    private long transaction_amount=0;
}
