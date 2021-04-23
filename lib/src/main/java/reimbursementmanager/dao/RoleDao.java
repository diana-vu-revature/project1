package reimbursementmanager.dao;

import reimbursementmanager.DBConnection;
import reimbursementmanager.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.*;

public class RoleDao {
  private Connection connection;

  private static final Logger log = LogManager.getLogger(RoleDao.class);

  public RoleDao() {
    connection = new DBConnection().getConnection();
  }
  
  //create
  public void add(Role role) {
    try {
      PreparedStatement pStatement = connection.prepareStatement("insert into user_role(role_name) values (?)");
      pStatement.setInt(2, role.getId());
      pStatement.setString(3, role.getName());
      int rowCount = pStatement.executeUpdate();

      if(rowCount > 0)
        log.debug("Added Role to database");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  //read
  public Role getById(int id) {
    Role role = null;
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from user_role where role=" + id);
      ResultSet set = pStatement.executeQuery();

      role = new Role(
        set.getInt("id"),
        set.getString("name")
      );
  
      //log.debug("Got Role: " + role);
      } catch (SQLException e){
        e.printStackTrace();
      }
      
      return role;
  }

}
