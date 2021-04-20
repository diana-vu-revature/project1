package reimbursementmanager.dao;

import reimbursementmanager.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao {
  Connection connection;
  Role role;
  
  //create
  public void add(Role role) {
    try {
      PreparedStatement pStatement = connection.prepareStatement("insert into user_role(role_name) values (?)");
      pStatement.setInt(2, role.getId());
      pStatement.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  //read
  public Role getById(Role role) {
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from user_role where role=" + role);
      ResultSet set = pStatement.executeQuery();

      // Role role = new Role(
      //   set.getInt("id"),
      //   set.get
      // );
  
      } catch (SQLException e){
        e.printStackTrace();
      }
      
      return role;
  }

  public RoleDao(Connection connection, Role role) {
    this.connection = connection;
    this.role = role;
  }

}
