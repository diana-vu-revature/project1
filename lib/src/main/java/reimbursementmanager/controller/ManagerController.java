package reimbursementmanager.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.*;

import reimbursementmanager.service.UserService;
import reimbursementmanager.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manager")
public class ManagerController extends HttpServlet {
  
  private static final Logger log = LogManager.getLogger(LoginController.class);
  
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
    // get current user
    HttpSession session = req.getSession(false);
    int userId = (int) session.getAttribute("userId");
    User user = UserService.getById(userId);

    // return view with user's name
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    String msg = "<p id=\"hello\">Hello, "+ user.getFname() + " " + user.getSurname() + "</p>";
    out.print(msg);

    req.getRequestDispatcher("manager.html").include(req, res);
  }
  
}
