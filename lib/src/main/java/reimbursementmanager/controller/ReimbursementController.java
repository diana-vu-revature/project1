package reimbursementmanager.controller;

// https://www.baeldung.com/servlet-json-response
// TODO: doGet, get req parameters, use gson to create json from reimbursement object
// TODO: doPost to add Reimbursement
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.*;

import reimbursementmanager.model.Reimbursement;
import reimbursementmanager.model.Role;
import reimbursementmanager.model.User;
import reimbursementmanager.service.ReimbursementService;
import reimbursementmanager.service.RoleService;
import reimbursementmanager.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;

@WebServlet({"/reimbursement", "/reimbursement/all"})
public class ReimbursementController extends HttpServlet {
  private static final Logger log = LogManager.getLogger(ReimbursementController.class);
  private Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
    log.debug("request to " + req.getRequestURL());

    // Get current user
    HttpSession session = req.getSession(false);
    int userId = (int) session.getAttribute("userId");
    User user = UserService.getById(userId);
    log.debug(user);
    
    String uri = req.getRequestURI();
    
    if(uri.equalsIgnoreCase("/reimbursement/all")) {
      // get all reimbursements to turn to JSON to send in response
      List<Reimbursement> reim = ReimbursementService.getAll();
      String reimbursementJsonString = gson.toJson(reim);

      log.debug("Got reimbursements: " + reim);
      log.debug("Sending JSON for reimbursements: " + reimbursementJsonString);

      PrintWriter out = resp.getWriter();
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      out.print(reimbursementJsonString);
      out.flush();
    } else if(req.getParameter("approve") != null || req.getParameter("deny") != null) {
      String approveReimId = req.getParameter("approve");
      String denyReimId = req.getParameter("deny");

      if(approveReimId != null) {
        int reimId = Integer.parseInt(approveReimId);
        Reimbursement reimToUpdate = ReimbursementService.getById(reimId);
        reimToUpdate.setManagerId(user.getId());
        reimToUpdate.setResolved(true);
        reimToUpdate.setApprove(true);
        ReimbursementService.update(reimToUpdate);
        log.debug(user.getFname() + " " + user.getSurname() + " approved reimbursement #" + reimId);
      } else if(denyReimId != null) {
        int reimId = Integer.parseInt(denyReimId);
        Reimbursement reimToUpdate = ReimbursementService.getById(reimId);
        reimToUpdate.setManagerId(user.getId());
        reimToUpdate.setResolved(true);
        reimToUpdate.setApprove(false);
        ReimbursementService.update(reimToUpdate);
        log.debug(user.getFname() + " " + user.getSurname() + " denied reimbursement #" + reimId);
      }
    } else if(uri.equalsIgnoreCase("/reimbursement")) {
      // get reimbursements for current user to turn to JSON to send in response
      Role userRole = RoleService.getById(user.getRoleId());
      List<Reimbursement> reim = null;
      if(userRole.getName().equalsIgnoreCase("manager")) {
        reim = ReimbursementService.getByManagerId(user.getId());
      } else {
        reim = ReimbursementService.getByEmployeeId(user.getId());
      }

      String reimbursementJsonString = gson.toJson(reim);

      PrintWriter out = resp.getWriter();
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      out.print(reimbursementJsonString);
      out.flush();
    }
    
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
    // TODO: get parameters, set to Reimbursement object, add to db
    // Get current user
    HttpSession session = req.getSession(false);
    int userId = (int) session.getAttribute("userId");
    User user = UserService.getById(userId);

    String id = req.getParameter("id");
    String name = req.getParameter("name");
    String price = req.getParameter("price");
    String employeeId = req.getParameter("employeeId");
    String managerId = req.getParameter("managerId");
    String approved = req.getParameter("approved");
    String resolved = req.getParameter("resolved");

    int reimbursementId = 0;
    if(id != null) {
      reimbursementId = Integer.parseInt(id);
    }

    Boolean isApproved = Boolean.parseBoolean(approved);
    Boolean isResolved = Boolean.parseBoolean(resolved);
    double money = Double.parseDouble(price);

    Integer eId = null;
    if(employeeId != null) {
      eId = Integer.parseInt(employeeId);
    } else {
      eId = user.getId();
    }

    Integer mId = null;
    if(managerId != null) {
      mId = Integer.parseInt(managerId);
    }
    
    Reimbursement reim = new Reimbursement(reimbursementId, name, money, eId, mId, isApproved, isResolved);

    ReimbursementService.add(reim);

    // redirect back to employee or manager homepage
    Role r = RoleService.getById(user.getRoleId());
    if(r.getName().equalsIgnoreCase("manager")) {
      res.sendRedirect(req.getContextPath() + "/manager");
    } else {
      res.sendRedirect(req.getContextPath() + "/employee");
    }
  }
}
