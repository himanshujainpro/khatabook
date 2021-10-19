package io.khatabook.controller;

import io.khatabook.dto.CreateUserDto;
import io.khatabook.dto.CustomerListDto;
import io.khatabook.model.User;
import io.khatabook.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {


    private UserService userService;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        final User createdUser = userService.createUser(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping()
    public ResponseEntity<User> findUser(@RequestParam(name = "id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(id));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerListDto>> findCustomers(@RequestParam(name = "userId") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findCustomers(id));
    }

    @GetMapping("balance")
    public ResponseEntity<Long> findDebitBalance(@RequestParam(name = "userId") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getBalance(id));
    }

    @GetMapping("business-name")
    public ResponseEntity<String> findBusinessName(@RequestParam(name = "userId") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getBusinessName(id));
    }

    @GetMapping("uid")
    public ResponseEntity<Long> findUserId(@RequestParam(name = "phone") long phone) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserIdByUserMobile(phone));
    }

}
