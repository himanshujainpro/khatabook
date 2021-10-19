package io.khatabook.mapper;


import io.khatabook.dto.CreateUserDto;
import io.khatabook.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User createUserDtoUser(CreateUserDto createUserDto);
}
