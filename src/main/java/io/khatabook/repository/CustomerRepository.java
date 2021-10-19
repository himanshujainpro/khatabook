package io.khatabook.repository;

import io.khatabook.dto.CustomerListDto;
import io.khatabook.dto.CustomerNamePhoneBalanceDto;
import io.khatabook.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select new io.khatabook.dto.CustomerListDto(c.customer_id,c.name,c.balance) from Customer c where c.user.user_id = :id")
    List<CustomerListDto> findByUser_User_id(long id);

    @Query("select (count(c) > 0) from Customer c where c.phone = ?1")
    boolean existsCustomerByPhone(String phone);

    @Query("select new io.khatabook.dto.CustomerNamePhoneBalanceDto(c.name,c.phone,c.balance) from Customer c where c.customer_id = :id")
    CustomerNamePhoneBalanceDto findByCustomer_id(long id);


    @Query("select new java.lang.String(c.name) from Customer c where c.customer_id = :id")
    String findName(long id);

    @Modifying
    @Query("update Customer c set  c.balance =?2 where c.customer_id=?1")
    void updateBalance(long id, long newBalance);

    @Query("select new java.lang.Long(c.balance) from Customer c where c.customer_id = :id")
    Long findBalance(long id);

    @Query("select new java.lang.Long(c.user.user_id) from Customer c where c.customer_id = :id")
    Long findUserId(long id);
}