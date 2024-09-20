package com.neoteric.javaFullStack.service;

import com.neoteric.javaFullStack.entity.AccountAddressEntity;
import com.neoteric.javaFullStack.entity.AccountEntity;
import com.neoteric.javaFullStack.model.Account;
import com.neoteric.javaFullStack.model.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Objects;

public class JPAAccountSearchService {
    

    public Account searchAccountUsingJPA(String accountNumber){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("javaFullStack");

        EntityManager entityManager= emf.createEntityManager();

        Query query= entityManager.createQuery("FROM AccountEntity a WHERE a.account_number = :accountNumberFromUI");

        query.setParameter("accountNumberFromUI",accountNumber);

        List<AccountEntity> accountEntities = query.getResultList();
       AccountEntity accountEntity= accountEntities.get(0);

        System.out.println("Account is loading");

        Account account= Account.builder()
                .account_number(accountEntity.getAccount_number())
                .mobile(accountEntity.getMobile())
                .balance(accountEntity.getBalance())
                .pan(accountEntity.getPan())
                . build();

        List<AccountAddressEntity> accountAddressEntityList = accountEntity.getAccountAddressEntityList();

        if(Objects.nonNull(accountAddressEntityList)&& accountAddressEntityList.size()>0) {
            AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);

            Address address = new Address();

            address.setAdd1(accountAddressEntity.getAddress1());
            address.setAdd2(accountAddressEntity.getAddress2());
            address.setCity(accountAddressEntity.getCity());
            address.setState(accountAddressEntity.getState());
            address.setPincode(accountAddressEntity.getPincode());
            account.setAddress(address);
        }

return account;
    }

}
