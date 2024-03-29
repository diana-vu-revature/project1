package reimbursementmanager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.*;

import reimbursementmanager.model.User;
import reimbursementmanager.model.Role;
import reimbursementmanager.service.RoleService;
import reimbursementmanager.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

@WebServlet({"/user", "/employees", "/managers"})
public class UserController extends HttpServlet{
  private static final Logger log = LogManager.getLogger(ReimbursementController.class);
  private Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
    
    String uri = req.getRequestURI();

    if(uri.equalsIgnoreCase("/employees")) {
      List<Role> roles = RoleService.getAll();
      Role employee = roles.stream().filter(r -> r.getName().equalsIgnoreCase("employee"))
        .findFirst()
        .orElse(null);
      int roleId = employee.getId();
      List<User> employees = UserService.getByRoleId(roleId);
      String employeesString = gson.toJson(employees);

      PrintWriter out = resp.getWriter();
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      out.print(employeesString);
      out.flush();
    } else if(uri.equalsIgnoreCase("/managers")) {
      List<Role> roles = RoleService.getAll();
      Role manager = roles.stream().filter(r -> r.getName().equalsIgnoreCase("manager"))
      .findFirst()
      .orElse(null);
      int roleId = manager.getId();
      List<User> managers = UserService.getByRoleId(roleId);
      String managersString = gson.toJson(managers);

      PrintWriter out = resp.getWriter();
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      out.print(managersString);
      out.flush();
    } else {
      //getId parameter return as Json
      HttpSession session = req.getSession(false);
      int userId = (int) session.getAttribute("userId");
      User user = UserService.getById(userId);

      String userString = gson.toJson(user);

      PrintWriter out = resp.getWriter();
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      out.print(userString);
      out.flush();
    }

  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
    // TODO: get parameters, set to Reimbursement object, add to db
    String id = req.getParameter("id");
    String role = req.getParameter("roleId");
    String fname = req.getParameter("fname");
    String surname = req.getParameter("surname");
    String email = req.getParameter("email");
    String password = req.getParameter("pword");

    int userId = 0;
    if(id != null) {
      userId = Integer.parseInt(id);
    }

    int roleId = Integer.parseInt(role);
    
    User user = new User(userId, roleId, fname, surname, email, password);

    if(user.getId() != 0) {
      UserService.update(user);
      // redirect back to employee or manager homepage
      Role r = RoleService.getById(user.getRoleId());
      if(r.getName().equalsIgnoreCase("manager")) {
        res.sendRedirect(req.getContextPath() + "/manager");
      } else {
        res.sendRedirect(req.getContextPath() + "/employee");
      }
      log.debug("Update user: " + user);
    } else {
      UserService.add(user);
      // redirect back to login
      res.sendRedirect(req.getContextPath() + "/login");
      log.debug("Added user: " + user);
    }
  }
}
