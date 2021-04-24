package reimbursementmanager.controller;

// https://www.baeldung.com/servlet-json-response
// TODO: doGet, get req parameters, use gson to create json from reimbursement object
// TODO: doPost to add Reimbursement
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.*;

import reimbursementmanager.model.Reimbursement;
import reimbursementmanager.model.User;
import reimbursementmanager.service.ReimbursementService;
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

    // Get current user
    HttpSession session = req.getSession(false);
    int userId = (int) session.getAttribute("userId");
    User user = UserService.getById(userId);
    
    String uri = req.getRequestURI();

    if(uri.equalsIgnoreCase("/reimbursement/all")) {
      // TODO: get all reimbursements and turn to JSON and print using response getWriter
      List<Reimbursement> reim = ReimbursementService.getAll();
      String reimbursementJsonString = gson.toJson(reim);

      PrintWriter out = resp.getWriter();
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      out.print(reimbursementJsonString);
      out.flush();
    } else {
      // TODO: get reimbursement for user for current user
    }
    
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
    // TODO: get parameters, set to Reimbursement object, add to db
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

    boolean isApproved = Boolean.parseBoolean(approved);
    boolean isResolved = Boolean.parseBoolean(resolved);
    double money = Double.parseDouble(price);
    int eId = Integer.parseInt(employeeId);
    int mId = Integer.parseInt(managerId);
    
    Reimbursement reim = new Reimbursement(reimbursementId, name, money, eId, mId, isApproved, isResolved);

    ReimbursementService.add(reim);
  }
}