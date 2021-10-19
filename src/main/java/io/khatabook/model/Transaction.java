package io.khatabook.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.khatabook.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long transaction_id;

    @JsonBackReference
    @ManyToOne
    private Customer customer;

    private String details_text;

    private LocalDateTime transaction_date;

    private TransactionType transaction_type;

    private String bill_photo_url;

    private long transaction_amount=0;
}
