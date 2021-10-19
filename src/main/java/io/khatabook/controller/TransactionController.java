package io.khatabook.controller;

import io.khatabook.dto.CreateTransactionDto;
import io.khatabook.dto.TransactionListDto;
import io.khatabook.model.Transaction;
import io.khatabook.repository.CustomerRepository;
import io.khatabook.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final CustomerRepository customerRepository;

    @PostMapping()
    public ResponseEntity<Transaction> doTransaction(@RequestBody CreateTransactionDto createTransactionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.doTransaction(createTransactionDto));
    }

    @GetMapping()
    public ResponseEntity<TransactionListDto> findTransactions(@RequestParam(name = "customer_id") long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.findTransactions(id));
    }

}
