package io.khatabook.service;

import io.khatabook.dto.CreateUserDto;
import io.khatabook.dto.CustomerListDto;
import io.khatabook.exception.DataNotExistException;
import io.khatabook.mapper.UserMapper;
import io.khatabook.model.User;
import io.khatabook.repository.CustomerRepository;
import io.khatabook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private CustomerRepository customerRepository;


    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public User createUser(CreateUserDto createUserDto) {
        return userRepository.save(userMapper.createUserDtoUser(createUserDto));
    }

    public User findUser(long id) {
        if (userRepository.existsById(id)) return userRepository.findByUser_id(id);
        else throw new DataNotExistException("User id:" + id + " is not exist");
    }

    public List<CustomerListDto> findCustomers(long id) {

        if(userRepository.existsById(id)){
            return customerRepository.findByUser_User_id(id);
        }
        else throw new DataNotExistException("User id:" + id + " is not exist");
    }

    public long getBalance(long id){
        if(userRepository.existsById(id)){
            return userRepository.findBalance(id);
        }
        else throw new DataNotExistException("User id:" + id + " is not exist");
    }


    public String  getBusinessName(long id){
        if(userRepository.existsById(id)){
            return userRepository.findBusinessName(id);
        }
        else throw new DataNotExistException("User id:" + id + " is not exist");
    }

    public Long getUserIdByUserMobile(long phone){
        if(userRepository.existsUserByPhone(phone)){
            return userRepository.findUserId(phone);
        }
        else throw new DataNotExistException("User mobile:" + phone + " is not exist");
    }

}
