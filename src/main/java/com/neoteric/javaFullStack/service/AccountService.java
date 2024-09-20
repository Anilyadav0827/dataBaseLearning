package com.neoteric.javaFullStack.service;

import com.neoteric.javaFullStack.entity.AccountAddressEntity;
import com.neoteric.javaFullStack.entity.AccountEntity;
import com.neoteric.javaFullStack.exception.AccountCreationFailedException;
import com.neoteric.javaFullStack.hibernate.HibernateUtils;
import com.neoteric.javaFullStack.model.Account;
import com.neoteric.javaFullStack.model.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jdk.jfr.Percentage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService {

    public String createAccountUsingHibernate(Account account){

        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();

        Transaction transaction= session.beginTransaction();

        AccountEntity accountEntity= new AccountEntity();
        accountEntity.setAccount_number(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
           accountEntity.setMobile(account.getMobile());
        accountEntity.setBalance(account.getBalance());

         session.persist(accountEntity);

         transaction.commit();

         return accountEntity.getAccount_number();
    }



    public String createAccountUsingJPa(Account account){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("javaFullStack");
        EntityManager entityManager= emf.createEntityManager();
        entityManager.getTransaction().begin();

        AccountEntity accountEntity= new AccountEntity();
        accountEntity.setAccount_number(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setMobile(account.getMobile());
        accountEntity.setBalance(account.getBalance());
        List<AccountAddressEntity> accountAddressEntityList= new ArrayList<>();

        AccountAddressEntity accountAddressEntity= new AccountAddressEntity();

        accountAddressEntity.setAddress1(account.getAddress().getAdd1());
        accountAddressEntity.setAddress2(account.getAddress().getAdd2());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setAccountEntityTest(accountEntity);
        accountAddressEntity.setStatus(1);
        accountAddressEntityList.add(accountAddressEntity);

        accountEntity.setAccountAddressEntityList(accountAddressEntityList);

        entityManager.persist(accountEntity);
        entityManager.getTransaction().commit();




        return account.getAccount_number();

    }


    public Account searchAccountUsingHibernateHeaders(String accountNumber){

        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();

//       Query<AccountEntity>query=session.createQuery("From AccountEntity a where a.account_number=account_number");
//
//        //Transaction transaction= session.beginTransaction();
//
//
//       // query.setParameter("accountNumberFromUI",accountNumber);
//        query.setParameter("accountNumberFromUI",accountNumber);

        Query<AccountEntity> query = session.createQuery("FROM AccountEntity a WHERE a.account_number = :accountNumberFromUI");

        // Set the value for the named parameter
        query.setParameter("accountNumberFromUI", accountNumber);
        AccountEntity accountEntity = query.list().get(0);


        System.out.println("Account is loading");

        Account account= Account.builder()
                        .account_number(accountEntity.getAccount_number())
                .mobile(accountEntity.getMobile())
                .balance(accountEntity.getBalance())
                        .pan(accountEntity.getPan())
                .address(
                        Address.builder()
                                .add1(accountEntity.getAccountAddressEntityList().get(0).getAddress1())
                                .add2(accountEntity.getAccountAddressEntityList().get(0).getAddress2())
                                .city(accountEntity.getAccountAddressEntityList().get(0).getCity())
                                .state(accountEntity.getAccountAddressEntityList().get(0).getState())
                                .pincode(accountEntity.getAccountAddressEntityList().get(0).getPincode()).build()
                )
                .build();


        return account;

    }


    public String createAccountUsingHibernateFromUI(Account account){

        SessionFactory sessionFactory= HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();

        Transaction transaction= session.beginTransaction();

        AccountEntity accountEntity= new AccountEntity();
        accountEntity.setAccount_number(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setMobile(account.getMobile());
        accountEntity.setBalance(account.getBalance());

        List<AccountAddressEntity> accountAddressEntityList= new ArrayList<>();

        AccountAddressEntity accountAddressEntity= new AccountAddressEntity();

        accountAddressEntity.setAddress1(account.getAddress().getAdd1());
        accountAddressEntity.setAddress2(account.getAddress().getAdd2());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setAccountEntityTest(accountEntity);
        accountAddressEntity.setStatus(1);
        accountAddressEntityList.add(accountAddressEntity);

        accountEntity.setAccountAddressEntityList(accountAddressEntityList);

        session.persist(accountEntity);

        transaction.commit();

        return accountEntity.getAccount_number();
    }




    public String createAccount(Account account) throws AccountCreationFailedException{
        String accountNumber= null;
 try {
    Connection connection = DBConnection.getConnection();
    Statement statement = connection.createStatement();
    accountNumber= UUID.randomUUID().toString();

     String query = "insert in banking_app.account value("+"'"+account.getAccount_number()+"'"
             +","+"'"+account.getName()+"'"
             +","+"'"+account.getPan()+"'"
             +","+"'"+account.getBalance()+"'"
             +","+"'"+account.getMobile()+"'"+")";
     int status= statement.executeUpdate(query);

     if(status==1){
         System.out.println("account is created "+accountNumber);

     }
     else {
         throw new AccountCreationFailedException("account creation failed"+account.getPan());

     }
  }
 catch (SQLException e){
     System.out.println("Sql exception"+e);

 }
 catch (AccountCreationFailedException e){
     System.out.println("exception occurred"+e);
     throw e;
   }

 return accountNumber;
    }

}
