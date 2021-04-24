package reimbursementmanager.service;

import reimbursementmanager.dao.RoleDao;
import reimbursementmanager.model.Role;

public class RoleService {
  private static RoleDao roleDao = new RoleDao();

  private RoleService() {}

  public static Role getById(int id) {
    return roleDao.getById(id);
  }

}
