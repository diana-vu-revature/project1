package reimbursementmanager.dao;
import reimbursementmanager.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDao {
  Connection connection;
  Role role;

  public void create(Role role){
    try {
      PreparedStatement pStatement = connection.prepareStatement("insert into user_role(role_name) values (?)");
      pStatement.setInt(2, role.getId());
      pStatement.executeUpdate();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
  }

  public RoleDao(Connection connection, Role role) {
    this.connection = connection;
    this.role = role;
  }
  
}
