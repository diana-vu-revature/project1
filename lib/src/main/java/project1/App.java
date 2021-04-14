package project1;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class App {
    
    //for connecting to postgres
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String username = "hiworld";
    String password = "password";

    try {
        Connection connection = DriverManager.getConnection(url, username, password);

        Employee bob = new Employee(id, fname, size, cheese, pepperoni);

        EmployeeDao eDi = new EmployeeDao(connection, bob);

        eDi.start();

    } catch (SQLException e) {

        e.printStackTrace();
    }
    
}
