package io.khatabook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    private String name;

    private String email;

    private long phone;

    private String business_name;

    private String image_url;
}
