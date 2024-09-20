package com.neoteric.javaFullStack.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ATM {
private  String card_number;
private String pin;
private String cvv;
private Date expiry;
private String account_number;
}
