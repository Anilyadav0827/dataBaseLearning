package com.neoteric.javaFullStack.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Account {

    private  String account_number;
    private String name;
    private String pan;
    private String balance;
    private String mobile;
    private Address address;

}
