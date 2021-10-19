package io.khatabook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerNamePhoneBalanceDto {
    private String name;
    private String phone;
    private long balance=0;
}
