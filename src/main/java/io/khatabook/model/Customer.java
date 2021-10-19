package io.khatabook.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.khatabook.enums.Language;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customer_id;


    @JsonBackReference
    @ManyToOne()
    private User user;

    private String name;

    private String email;

    private String phone;

    private String image_url;

    private Language language;

    private String address;

    private long balance = 0;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Collection<Transaction> transactions = new ArrayList<>();
}
