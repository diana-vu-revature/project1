package reimbursementmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.*;

public class DBConnection {
  private Connection connection;

  private static final Logger log = LogManager.getLogger(DBConnection.class);

  public DBConnection() {
    // for connecting to postgres
    String url = "jdbc:postgresql://localhost:3306/postgres";
    String username = "hiworld";
    String password = "password";

    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(url, username, password);

      if(connection != null) {
        log.debug("Connected to the database!");
      } else {
        log.debug("Failed to connect to the database!");
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return this.connection;
  }
  
}
