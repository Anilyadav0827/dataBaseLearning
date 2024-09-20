package com.neoteric.javaFullStack.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
@Table(name = "account",schema = "bank")
public class AccountEntity {
    @Id
    @Column(name="account_number",nullable = false)
    private  String account_number;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="pan",nullable = false)
    private String pan;

    @Column(name="balance" ,nullable = false)
    private String balance;

    @Column(name="mobile", nullable = false)
    private String mobile;

    @OneToMany(mappedBy = "accountEntityTest",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    //lazy loading means it fetch the data and parent only it loads the child only when  we are called or required
    //example if write a query to load the account it only load account
    //eager loading means it says the Hibernate to load  the parent and child classes
    //example if write a query to load the account it only load account and its related child querys like address

    public List<AccountAddressEntity> accountAddressEntityList;
}
