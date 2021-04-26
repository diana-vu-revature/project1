package reimbursementmanager.service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import reimbursementmanager.model.User;
import reimbursementmanager.model.Role;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebFilter;

@WebFilter({"/manager", "/manager/*", "/reimbursement/all", "/employees", "/reimbursement?approve=*", "/reimbursement?deny=*"})
public class ManagerValidator implements Filter{

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (request instanceof HttpServletRequest) {
      HttpSession session = ((HttpServletRequest) request).getSession(false);
      
      if(session != null && session.getAttribute("userId") != null) {
        int userId = (int) session.getAttribute("userId");
        User user = UserService.getById(userId);
        Role role = RoleService.getById(user.getRoleId());

        if(role.getName().equalsIgnoreCase("manager")){
          chain.doFilter(request, response);
        } else {
          // TODO: return 401 response
        }
      } else {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<p id=\"alert\">Not Authorized. Please login!</p>");

        request.getRequestDispatcher("login.html").include(request, response);
      }
    }
  }
}
