//package project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDao {
    Connection connection;
    Employee employee;

    public void insert(Employee employee) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("insert into employees(fname, surname, email, reimbursement) values (?, ?, ?, ?)");
            pStatement.setString(2, employee.getFname());
            pStatement.setString(3, employee.getSurname());
            pStatement.setString(4, employee.getEmail());
            pStatement.setDouble(5, employee.getReimbursement());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public EmployeeDao(Connection connection, Employee employee) {
        this.connection = connection;
        this.employee = employee;
    }

}