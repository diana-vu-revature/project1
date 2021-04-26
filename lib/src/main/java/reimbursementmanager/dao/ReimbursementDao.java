package reimbursementmanager.dao;

import reimbursementmanager.DBConnection;
import reimbursementmanager.model.Reimbursement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ReimbursementDao {
  private Connection connection;

  public ReimbursementDao() {
    connection = new DBConnection().getConnection();
  }

  //create
  public void add(Reimbursement reimbursement) {
    try {
      PreparedStatement pStatement = connection.prepareStatement("insert into reimbursement(reim_name, employee_id, manager_id, price, approved, resolved) values (?, ?, ?, ?, ?, ?)");
      pStatement.setString(1, reimbursement.getName());
      pStatement.setInt(2, reimbursement.getEmployeeId());
      if(reimbursement.getManagerId() != null) {
        pStatement.setInt(3, reimbursement.getManagerId());
      } else {
        pStatement.setNull(3, java.sql.Types.INTEGER);
      }
      pStatement.setDouble(4, reimbursement.getPrice());
      pStatement.setBoolean(5, reimbursement.isApproved());
      pStatement.setBoolean(6, reimbursement.isResolved());
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
      PreparedStatement pStatement = connection.prepareStatement("select * from reimbursement where id=" + id);
      ResultSet set = pStatement.executeQuery();

      if(set.next() != false) {
        reimbursement = new Reimbursement(
        set.getInt("id"),
        set.getString("reim_name"),
        set.getDouble("price"),
        set.getInt("employee_id"),
        set.getInt("manager_id"),
        set.getBoolean("approved"),
        set.getBoolean("resolved")
        );
      }
      
    } catch (SQLException e){
      e.printStackTrace();
    }
      
    return reimbursement;
  }

  public List<Reimbursement> getByEmployeeId(int id) {
    List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from reimbursement where employee_id=" + id);
      ResultSet set = pStatement.executeQuery();

      //TODO: loop for result set to get all reimbursements
      while(set.next() != false) {
        Reimbursement reimbursement = new Reimbursement(
        set.getInt("id"),
        set.getString("reim_name"),
        set.getDouble("price"),
        set.getInt("employee_id"),
        set.getInt("manager_id"),
        set.getBoolean("approved"),
        set.getBoolean("resolved")
        );
        reimbursementList.add(reimbursement);
      }
      

    } catch (SQLException e){
      e.printStackTrace();
    }
      
    return reimbursementList;
  }

  public List<Reimbursement> getByManagerId(int id) {
    List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from reimbursement where manager_id=" + id);
      ResultSet set = pStatement.executeQuery();

      //TODO: loop for result set to get all reimbursements
      while(set.next() != false) {
        Reimbursement reimbursement = new Reimbursement(
        set.getInt("id"),
        set.getString("reim_name"),
        set.getDouble("price"),
        set.getInt("employee_id"),
        set.getInt("manager_id"),
        set.getBoolean("approved"),
        set.getBoolean("resolved")
        );
        reimbursementList.add(reimbursement);
      }

    } catch (SQLException e){
      e.printStackTrace();
    }
      
    return reimbursementList;
  }

  public List<Reimbursement> getAll() {
    List<Reimbursement> reimbursementList = new ArrayList<Reimbursement>();
    try{
      PreparedStatement pStatement = connection.prepareStatement("select * from reimbursement");
      ResultSet set = pStatement.executeQuery();

      //TODO: loop for result set to get all reimbursements
      while(set.next() != false) {
        Reimbursement reimbursement = new Reimbursement(
        set.getInt("id"),
        set.getString("reim_name"),
        set.getDouble("price"),
        set.getInt("employee_id"),
        set.getInt("manager_id"),
        set.getBoolean("approved"),
        set.getBoolean("resolved")
        );
        reimbursementList.add(reimbursement);
      }

    } catch (SQLException e){
      e.printStackTrace();
    }
      
    return reimbursementList;
  }

  // update
  public void update(Reimbursement reimbursement) {
    // UPDATE table_name SET column1 = value1, column2 = value2...., columnN = valueN WHERE id=;
    try{
      PreparedStatement pStatement = connection
        .prepareStatement("UPDATE reimbursement SET reim_name=?, manager_id=?, employee_id=?, price=?, resolved=?, approved=? WHERE id=?");
      pStatement.setString(1, reimbursement.getName());
      pStatement.setInt(2, reimbursement.getManagerId());
      pStatement.setInt(3, reimbursement.getEmployeeId());
      pStatement.setDouble(4, reimbursement.getPrice());
      pStatement.setBoolean(5, reimbursement.isResolved());
      pStatement.setBoolean(6, reimbursement.isApproved());
      pStatement.setInt(7, reimbursement.getId());
      pStatement.executeUpdate();

    } catch (SQLException e){
      e.printStackTrace();
    }
  }

}