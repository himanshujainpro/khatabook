package io.khatabook.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    private String name;

    private String email;

    private long phone;

    private String business_name;

    private String image_url;

    private long balance=0;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Collection<Customer> customers = new HashSet<>();
}
