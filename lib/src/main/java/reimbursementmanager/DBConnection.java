package reimbursementmanager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.*;

public class DBConnection {
  private Connection connection;

  private static final Logger log = LogManager.getLogger(DBConnection.class);

  public DBConnection() {
    // for connecting to postgres
    String propFileName = "config.properties";

    try(InputStream in = getClass().getClassLoader().getResourceAsStream(propFileName)) {
      Properties props = new Properties();
      props.load(in);

      Class.forName("org.postgresql.Driver");
      this.connection = DriverManager.getConnection(
        props.getProperty("url"), 
        props.getProperty("username"), 
        props.getProperty("password")
      );

      if(connection != null) {
        System.out.println("Connected to the database!");
        log.debug("Connected to the database!");
      } else {
        System.out.println("Failed to connect to the database!");
        log.debug("Failed to connect to the database!");
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return this.connection;
  }
  
}
