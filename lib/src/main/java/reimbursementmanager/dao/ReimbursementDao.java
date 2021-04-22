package reimbursementmanager.dao;

import reimbursementmanager.DBConnection;
import reimbursementmanager.model.Reimbursement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementDao {
  private Connection connection;

  public ReimbursementDao() {
    connection = new DBConnection().getConnection();
  }

  //create
  public void add(Reimbursement reimbursement) {
    try {
      PreparedStatement pStatement = connection.prepareStatement("insert into reimbursement(employee_id, manager_id, price, approved, resolved) values (?, ?, ?, ?, ?)");
      pStatement.setInt(2, reimbursement.getEmployeeId());
      pStatement.setInt(3, reimbursement.getManagerId());
      pStatement.setDouble(4, reimbursement.getPrice());
      pStatement.setBoolean(5, reimbursement.isApproved());
      pStatement.setBoolean(4, reimbursement.isResolved());
      pStatement.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  //read
  public Reimbursement getById(int id) {
    Reimbursement reimbursement = null;
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from reimbursemnt where id=" + id);
      ResultSet set = pStatement.executeQuery();

      reimbursement = new Reimbursement(
        set.getInt("id"),
        set.getString("name"),
        set.getDouble("price"),
        set.getInt("employee_id"),
        set.getInt("manager_id"),
        set.getBoolean("approved"),
        set.getBoolean("resolved")
      );
    } catch (SQLException e){
      e.printStackTrace();
    }
      
    return reimbursement;
  }

  public List<Reimbursement> getByEmployeeId(int id) {
    List<Reimbursement> reimbursementList = null;
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from reimbursemnt where employee_id=" + id);
      ResultSet set = pStatement.executeQuery();

      //TODO: loop for result set to get all reimbursements
      Reimbursement reimbursement = new Reimbursement(
        set.getInt("id"),
        set.getString("name"),
        set.getDouble("price"),
        set.getInt("employee_id"),
        set.getInt("manager_id"),
        set.getBoolean("approved"),
        set.getBoolean("resolved")
      );
      reimbursementList.add(reimbursement);

    } catch (SQLException e){
      e.printStackTrace();
    }
      
    return reimbursementList;
  }

  public List<Reimbursement> getByManagerId(int id) {
    List<Reimbursement> reimbursementList = null;
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from reimbursemnt where manager_id=" + id);
      ResultSet set = pStatement.executeQuery();

      //TODO: loop for result set to get all reimbursements
      Reimbursement reimbursement = new Reimbursement(
        set.getInt("id"),
        set.getString("name"),
        set.getDouble("price"),
        set.getInt("employee_id"),
        set.getInt("manager_id"),
        set.getBoolean("approved"),
        set.getBoolean("resolved")
      );
      reimbursementList.add(reimbursement);

    } catch (SQLException e){
      e.printStackTrace();
    }
      
    return reimbursementList;
  }

  public List<Reimbursement> getAll() {
    List<Reimbursement> reimbursementList = null;
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from reimbursemnt");
      ResultSet set = pStatement.executeQuery();

      //TODO: loop for result set to get all reimbursements
      Reimbursement reimbursement = new Reimbursement(
        set.getInt("id"),
        set.getString("name"),
        set.getDouble("price"),
        set.getInt("employee_id"),
        set.getInt("manager_id"),
        set.getBoolean("approved"),
        set.getBoolean("resolved")
      );
      reimbursementList.add(reimbursement);

    } catch (SQLException e){
      e.printStackTrace();
    }
      
    return reimbursementList;
  }

  // update

}