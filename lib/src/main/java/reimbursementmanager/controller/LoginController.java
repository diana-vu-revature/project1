package reimbursementmanager.controller;

import java.io.IOException;
import java.io.PrintWriter;

// import org.apache.logging.log4j.Logger;
// import org.apache.logging.log4j.LogManager;
//import org.slf4j.Logger;
import org.apache.log4j.*;

import reimbursementmanager.service.UserService;

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
    int roleId = Integer.parseInt(req.getParameter("role"));
    
    
    if(UserService.validLogin(email, password, roleId)) {
      // TODO: set user session to userId and return view
      HttpSession session = req.getSession();
      String sessionId = session.getId();
      session.setAttribute(email, sessionId);

      RequestDispatcher view = req.getRequestDispatcher("index.html");
      view.forward(req, res);

      //not sure if this is better
      res.setContentType("text/html"); 
      PrintWriter out = res.getWriter(); 

      // req.getParameter takes the value from login.html file where name is "email" 
      String n = req.getParameter("email"); 
      out.print("Welcome " + n); 
      out.print("<a href='SecondServlet?uname=" + n + "'>visit</a>"); 
      out.close();

    } else {
      // TODO: return login.html view with invalid message
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();
      out.print("Invalid login request. The email and password do not match any records in our system. Try again.");

      log.debug("Login post request failed.");
    }

    log.debug("Login post request received for email: " + email);
  }
  
}
