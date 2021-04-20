import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import reimbursementmanager.model.User;

public class UserDao {
    Connection connection;
    User user;

    public void create(User user) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("insert into users(role_id, fname, surname, email, pword) values (?, ?, ?, ?. ?)");
            pStatement.setInt(2, user.getRoleId());
            pStatement.setString(3, user.getFname());
            pStatement.setString(4, user.getSurname());
            pStatement.setString(5, user.getEmail());
            pStatement.setString(6, user.getPword());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public UserDao(Connection connection, User user) {
        this.connection = connection;
        this.user = user;
    }

}