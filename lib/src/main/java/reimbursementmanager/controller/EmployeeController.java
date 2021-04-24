package reimbursementmanager.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.*;

import reimbursementmanager.model.User;
import reimbursementmanager.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/employee")
public class EmployeeController extends HttpServlet {
  
  private static final Logger log = LogManager.getLogger(LoginController.class);
  
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
    HttpSession session = req.getSession(false);
    int userId = (int) session.getAttribute("userId");

    User user = UserService.getById(userId);

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.print("<p id=\"hello\">Hello, "+ user.getFname() + " " + user.getSurname() + "</p>");

    req.getRequestDispatcher("employee.html").include(req, res);
  }
  
}
