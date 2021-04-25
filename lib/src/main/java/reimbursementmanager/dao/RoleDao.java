package reimbursementmanager.dao;

import reimbursementmanager.DBConnection;
import reimbursementmanager.model.Role;
import reimbursementmanager.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
      pStatement.setString(1, role.getName());
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
      PreparedStatement pStatement = connection.prepareStatement("select * from user_role where id=" + id);
      ResultSet set = pStatement.executeQuery();
      
      if(set.next() != false) {
          role = new Role(
          set.getInt("id"),
          set.getString("role_name")
        );
    
        log.debug("Got Role: " + role);
      }
      
      } catch (SQLException e){
        e.printStackTrace();
      }
      
      return role;
  }

  public List<Role> getAll(){
    List<Role> roleList = new ArrayList<Role>();
    Role role = null;
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from user_role");
      ResultSet set = pStatement.executeQuery();
      
      while(set.next() != false) {
        role = new Role(
          set.getInt("id"),
          set.getString("role_name")
        );
        roleList.add(role);
        log.debug("Got roles: " + role);
      }
    } catch (SQLException e){
      e.printStackTrace();
    }
    return roleList;
  }
}
