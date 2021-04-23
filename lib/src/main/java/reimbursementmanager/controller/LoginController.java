package reimbursementmanager.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.*;

import reimbursementmanager.service.RoleService;
import reimbursementmanager.service.UserService;
import reimbursementmanager.model.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginController extends HttpServlet {
  
  private static final Logger log = LogManager.getLogger(LoginController.class);
  
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
    RequestDispatcher view = req.getRequestDispatcher("login.html");
    view.forward(req, res);
    log.debug("Login get request received.");
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    String roleParam = req.getParameter("role");

    System.out.println("email: " + email +"\nrole: " + roleParam + "\npassword: " + password);
    int roleId = Integer.parseInt(roleParam);

    log.debug("Login post request received for email: " + email);
    
    if(UserService.validLogin(email, password, roleId)) {
      // set session to userId
      int userId = UserService.getUserId(email, roleId);
      HttpSession session = req.getSession(true);
      session.setAttribute("userId", userId);

      Role role = RoleService.getById(roleId);
      if(role.getName().equalsIgnoreCase("employee")) {
        // TODO: if user is a employee go to employee home view
        RequestDispatcher eView = req.getRequestDispatcher("ehomepage.html");
        eView.forward(req, res);

      } else if(role.getName().equalsIgnoreCase("manager")) {
        // TODO: else if user is manager go to manager home view
        RequestDispatcher mView = req.getRequestDispatcher("mhomepage.html");
        mView.forward(req, res);
        
      }
    } else {
      // return login view with invalid login message
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();
      out.print("<p id=\"alert\">Invalid login request. The email and password do not match any records in our system. Try again.</p>");

      RequestDispatcher view = req.getRequestDispatcher("login.html");
      view.include(req, res);

      log.debug("Invalid login for user: " + email);
    }
  }
  
}
