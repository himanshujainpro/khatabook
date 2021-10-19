package io.khatabook.mapper;

import io.khatabook.dto.CreateCustomerDto;
import io.khatabook.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer dtoToCustomer(CreateCustomerDto createCustomerDto);
}
