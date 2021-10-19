package io.khatabook.controller;

import io.khatabook.dto.CreateCustomerDto;
import io.khatabook.model.Customer;
import io.khatabook.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        final Customer createdCustomer = customerService.createCustomer(createCustomerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestParam(name = "customer_id") long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted");
    }

    @GetMapping("/name")
    public ResponseEntity<String> findNameOfCustomer(@RequestParam(name = "customer_id") long id){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.findName(id));
    }
}
