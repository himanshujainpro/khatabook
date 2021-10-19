package io.khatabook.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CustomerListDto {
    private long customer_id;
    private String name;
    private long balance=0;
}
