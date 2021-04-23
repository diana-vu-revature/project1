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

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
  
  private static final Logger log = LogManager.getLogger(LoginController.class);
  
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
    log.debug("Logout request received.");
    // clear session
    req.getSession().invalidate();

    // return login view with logout message
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.print("<p id=\"logoutAlert\">You've been logged out.</p>");

    RequestDispatcher view = req.getRequestDispatcher("login.html");
    view.include(req, res);

    //log.debug("Logout for user: " + email);
  }
  
}
