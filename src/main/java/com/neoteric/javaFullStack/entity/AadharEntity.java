package com.neoteric.javaFullStack.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "aadhar",schema = "bank")
@Data
@NoArgsConstructor
public class AadharEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aadharNumber")
    public Integer aadharNumber;

    @Column(name = "name")
    public String name;

@OneToMany(mappedBy = "aadharEntity",cascade = CascadeType.ALL)// this aadharEntity from the child to know the
//  we are creating relationship child and parent
    public List<AddressEntity> addressEntities;

}
