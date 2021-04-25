package reimbursementmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import reimbursementmanager.dao.UserDao;
import reimbursementmanager.model.User;

public class UserService {
  private static UserDao userDao = new UserDao();

  private UserService() {}

  public static void add(User user) {
    userDao.add(user);
  }

  public static void update(User user) {
    userDao.update(user);
  }

  public static User getById(int id) {
    return userDao.getById(id);
  }

  public static boolean validLogin(String email, String password, int roleId) {
    User user = userDao.getByEmailRole(email, roleId);
    if(user != null) {
      if(user.getPword().equals(password))
        return true;
    }
    return false;
  }

  public static int getUserId(String email, int roleId) {
    User user = userDao.getByEmailRole(email, roleId);
    return user.getId();
  }

  // return list of user with manager role
  public static List<User> getByRoleId(int roleId) {
    List<User> allUsers = userDao.getAllUsers();
    
    //REVIEW later lambdas, filter, and streams
    List<User> filteredUsers = allUsers.stream().filter(u -> u.getRoleId() == roleId).collect(Collectors.toList());

    return filteredUsers;
  }

}
