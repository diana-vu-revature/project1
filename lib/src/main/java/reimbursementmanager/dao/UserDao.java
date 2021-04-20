package reimbursementmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import reimbursementmanager.DBConnection;
import reimbursementmanager.model.User;

public class UserDao {
  Connection connection;
  User user;

  private static final Logger log = LogManager.getLogger(UserDao.class);

  public UserDao() {
    connection = new DBConnection().getConnection();
  }
  
  // create
  public void add(User user) {
    try {
      PreparedStatement pStatement = connection
        .prepareStatement("insert into users(role_id, fname, surname, email, pword) values (?, ?, ?, ?. ?)");
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
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from users where id=" + id);
      ResultSet set = pStatement.executeQuery();
      
      User user = new User(
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

  public User getByEmailRole(String email, int role_id) {
    try{
      PreparedStatement pStatement = connection
        .prepareStatement("select * from users where email='" + email + "' and role_id=" + role_id);
      ResultSet set = pStatement.executeQuery();
      
      User user = new User(
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

  public UserDao(Connection connection, User user) {
    this.connection = connection;
    this.user = user;
  }

}