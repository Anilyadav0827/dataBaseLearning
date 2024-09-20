package com.neoteric.javaFullStack.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Entity
@Table(name = "account_address",schema = "bank")
@Data
@NoArgsConstructor
public class AccountAddressEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "address1")
    private String address1;
    @Column(name="address2")
    private String address2;
    @Column(name ="city")
    private String city;
    @Column (name = "state")
    private String state;
    @Column (name = "pincode")
    private String pincode;
    @Column(name="status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "accountNumber",referencedColumnName = "account_number")
    private AccountEntity accountEntityTest;


}
