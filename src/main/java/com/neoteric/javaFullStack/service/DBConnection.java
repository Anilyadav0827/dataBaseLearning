package com.neoteric.javaFullStack.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connection;



    public static Connection getConnection(){

        try {
            if(connection ==null) {
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",
                        "root", "root");
            }
            else{
                System.out.println("returning the existence code");
            }
        }
        catch (Exception e){
            System.out.println("Exception occurred while connection: "+e);

        }

return connection;
    }
}
