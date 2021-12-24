package com.energizeglobal.bank.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String nationalId;

    private String passportNumber;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Account> accounts;
}
