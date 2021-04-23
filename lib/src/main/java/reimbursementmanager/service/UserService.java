package reimbursementmanager.service;

import reimbursementmanager.dao.UserDao;
import reimbursementmanager.model.User;

public class UserService {
  private static UserDao userDao = new UserDao();

  public User getById(int id) {
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

}
