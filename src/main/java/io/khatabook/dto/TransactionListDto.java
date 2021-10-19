package io.khatabook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionListDto {
    private String name;

    public TransactionListDto(String name, String phone, long balance) {
        this.name = name;
        this.phone = phone;
        this.balance = balance;
    }

    private String phone;
    private long balance;
    private List<TransactionDto> transactions = new ArrayList<>();
}
