package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    int id;
    double reimbursement;
    String fname, surname, email;
    
    //for connecting to postgres
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String username = "hiworld";
    String password = "password";
    
    public void main(){

    try {
        Connection connection = DriverManager.getConnection(url, username, password);

    } catch (SQLException e) {

        e.printStackTrace();
    }
    
}
}
