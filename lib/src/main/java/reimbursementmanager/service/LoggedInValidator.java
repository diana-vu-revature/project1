package reimbursementmanager.service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebFilter;

@WebFilter({"/reimbursement"})
public class LoggedInValidator implements Filter{

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (request instanceof HttpServletRequest) {
      HttpSession session = ((HttpServletRequest) request).getSession(false);

      if(session != null && session.getAttribute("userId") != null) {
        chain.doFilter(request, response);
      }
    }

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.print("<p id=\"alert\">Not Authorized. Please login!</p>");

    request.getRequestDispatcher("login.html").include(request, response);
  }
  
}