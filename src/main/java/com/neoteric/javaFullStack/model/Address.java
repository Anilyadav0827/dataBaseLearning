package com.neoteric.javaFullStack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String add1;
    private String add2;
    private String pincode;
    private String city;
    private String state;
}
