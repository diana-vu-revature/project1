import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import reimbursementmanager.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    } else {
      // TODO: return login.html view with invalid message
    }

    log.debug("Login post request received for email: " + email);
  }
  
}
