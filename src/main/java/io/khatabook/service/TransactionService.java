package io.khatabook.service;

import io.khatabook.dto.CreateTransactionDto;
import io.khatabook.dto.CustomerNamePhoneBalanceDto;
import io.khatabook.dto.TransactionListDto;
import io.khatabook.enums.TransactionType;
import io.khatabook.exception.DataNotExistException;
import io.khatabook.mapper.TransactionMapper;
import io.khatabook.model.Customer;
import io.khatabook.model.Transaction;
import io.khatabook.repository.CustomerRepository;
import io.khatabook.repository.TransactionRepository;
import io.khatabook.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    //Heavy Operation

    @Transactional
    public Transaction doTransaction(CreateTransactionDto createTransactionDto) {
        Transaction transaction = transactionMapper.dtoToTransaction(createTransactionDto);
        long transactionAmount = getTransactionAmount(transaction.getTransaction_amount(),
                transaction.getTransaction_type());
        Optional<Customer> customerOptional = customerRepository.findById(createTransactionDto.getCustomer_id());

        if (customerOptional.isPresent()) {

            //Saving transaction
            Customer customer = customerOptional.get();
            transaction.setCustomer(customer);
            transactionRepository.save(transaction);

            //Updating customers balance
            long newBalanceOfCustomer = customer.getBalance() + transactionAmount;
            customerRepository.updateBalance(customer.getCustomer_id(), newBalanceOfCustomer);

            //Updating user's credit and debit balance
            long uId = customer.getUser().getUser_id();
            userRepository.updateBalance(uId,
                    userRepository.findBalance(uId) + transactionAmount);

            return transaction;
        } else {
            throw new DataNotExistException("Customer id:" + createTransactionDto.getCustomer_id() + " is not exist");
        }

    }

    public TransactionListDto findTransactions(long id) {

        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            CustomerNamePhoneBalanceDto customerNamePhoneBalanceDto = customerRepository.findByCustomer_id(id);
            TransactionListDto transactionListDto = new TransactionListDto(
                    customerNamePhoneBalanceDto.getName(),
                    customerNamePhoneBalanceDto.getPhone(),
                    customerNamePhoneBalanceDto.getBalance()
            );
            transactionListDto.setTransactions(transactionRepository.findTransactionsByCustomer_Customer_id(id));
            return transactionListDto;
        } else throw new DataNotExistException("Customer id:" + id + " is not exist");
    }

    private long getTransactionAmount(long amount, TransactionType transactionType) {
        if (transactionType == TransactionType.CREDIT) return amount;
        else return -amount;
    }
}
