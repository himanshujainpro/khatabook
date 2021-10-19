package io.khatabook.service;

import io.khatabook.dto.CreateCustomerDto;
import io.khatabook.exception.DataNotExistException;
import io.khatabook.exception.DuplicateEntityException;
import io.khatabook.mapper.CustomerMapper;
import io.khatabook.model.Customer;
import io.khatabook.model.User;
import io.khatabook.repository.CustomerRepository;
import io.khatabook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        long uid = createCustomerDto.getUser_id();
        String phone = createCustomerDto.getPhone();

        if (userRepository.existsById(uid)) {
            if (customerRepository.existsCustomerByPhone(phone)) {
                throw new DuplicateEntityException("Customer with phone :" + phone + " is already exist");
            } else {
                Customer customer = customerMapper.dtoToCustomer(createCustomerDto);
                User user = userRepository.findByUser_id(uid);
                customer.setUser(user);
                return customerRepository.save(customer);
            }
        } else throw new DataNotExistException("User id:" + uid + " is not exist");
    }

    @Transactional
    public void deleteCustomer(long id) {
        if (customerRepository.existsById(id)) {

            long customerBalance = customerRepository.findBalance(id);
            long uId = customerRepository.findUserId(id);
            long newUserBalance = 0;

            customerRepository.deleteById(id);

            newUserBalance = userRepository.findBalance(uId) - customerBalance;

            userRepository.updateBalance(uId, newUserBalance);


        } else throw new DataNotExistException("Customer id:" + id + " is not exist");
    }

    public String findName(long id) {
        if (customerRepository.existsById(id)) return customerRepository.findName(id);
        else throw new DataNotExistException("Customer id:" + id + " is not exist");
    }
}
