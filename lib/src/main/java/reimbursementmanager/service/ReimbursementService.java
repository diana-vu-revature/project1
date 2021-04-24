package reimbursementmanager.service;

import java.util.List;

import reimbursementmanager.dao.ReimbursementDao;
import reimbursementmanager.model.Reimbursement;

public class ReimbursementService {
  private static ReimbursementDao reimDao = new ReimbursementDao();

  private ReimbursementService() {}

  public static Reimbursement getById(int id) {
    return reimDao.getById(id);
  }

  public static List<Reimbursement> getByEmployeeId(int empId) {
    return reimDao.getByEmployeeId(empId);
  }

  public static List<Reimbursement> getByManagerId(int manId){
    return reimDao.getByManagerId(manId);
  }

  public static List<Reimbursement> getAll(){
    return reimDao.getAll();
  }

  public static Reimbursement add(Reimbursement r){
    return reimDao.add(r);
  }
}
