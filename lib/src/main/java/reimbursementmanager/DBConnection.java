package reimbursementmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  private Connection connection;

  public DBConnection() {
    // for connecting to postgres
    String url = "jdbc:postgresql://localhost:3306/postgres";
    String username = "hiworld";
    String password = "password";

    try {
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return this.connection;
  }
  
}
