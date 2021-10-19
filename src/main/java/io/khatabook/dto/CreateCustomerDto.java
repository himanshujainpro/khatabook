package io.khatabook.dto;

import io.khatabook.enums.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerDto {

    private long user_id;

    private String name;

    private String email;

    private String phone;

    private String image_url;

    private Language language;

    private String address;

}
