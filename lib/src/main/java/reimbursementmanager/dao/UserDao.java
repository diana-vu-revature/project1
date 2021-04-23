package reimbursementmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import org.apache.logging.log4j.Logger;
// import org.apache.logging.log4j.LogManager;
import org.apache.log4j.*;

import reimbursementmanager.DBConnection;
import reimbursementmanager.model.User;

public class UserDao{
  private Connection connection;

  private static final Logger log = LogManager.getLogger(UserDao.class);

  public UserDao() {
    connection = new DBConnection().getConnection();
  }
  
  // create
  public void add(User user) {
    try {
      PreparedStatement pStatement = connection
        .prepareStatement("insert into person(role_id, fname, surname, email, pword) values (?, ?, ?, ?. ?)");
      pStatement.setInt(2, user.getRoleId());
      pStatement.setString(3, user.getFname());
      pStatement.setString(4, user.getSurname());
      pStatement.setString(5, user.getEmail());
      pStatement.setString(6, user.getPword());
      pStatement.executeUpdate();
    } catch (SQLException e) {
  
      e.printStackTrace();
    }
  }

  // read
  public User getById(int id) {
    User user = null;
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from person where id=" + id);
      ResultSet set = pStatement.executeQuery();
      
      if(set.next() != false){
        user = new User(
          set.getInt("id"),
          set.getInt("role_id"),
          set.getString("fname"),
          set.getString("surname"),
          set.getString("email"),
          set.getString("pword")
        );
        log.debug("Got user: " + user);
      }

    } catch (SQLException e){
      e.printStackTrace();
    }
    
    return user;
  }

  public User getByEmailRole(String email, int role_id) {
    User user = null;
    try{
      PreparedStatement pStatement = connection
        .prepareStatement("select * from person where email='" + email + "' and role_id=" + role_id);
      ResultSet set = pStatement.executeQuery();
      
      user = new User(
        set.getInt("id"),
        set.getInt("role_id"),
        set.getString("fname"),
        set.getString("surname"),
        set.getString("email"),
        set.getString("pword")
      );

      log.debug("Got user: " + user);
    } catch (SQLException e){
      e.printStackTrace();
    }
    
    return user;
  }

}