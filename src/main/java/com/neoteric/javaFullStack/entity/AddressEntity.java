package com.neoteric.javaFullStack.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Table(name = "address",schema = "bank")
@Data
@NoArgsConstructor

public class AddressEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
    public Integer id;

@Column (name = "state")
    public String state;

//    @JoinColumn( name = "aadharNumber")
//    public AadharEntity aadharEntity;  this is unidirectional we will get only aadharNumber only we wont get name


@ManyToOne
@JoinColumn( name = "aadharNumber",referencedColumnName = "aadharNumber")
public AadharEntity aadharEntity;

}
