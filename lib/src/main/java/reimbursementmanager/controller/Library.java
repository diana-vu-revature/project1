package reimbursementmanager.controller;

import java.io.IOException;

import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class Library extends HttpServlet {
    public boolean someLibraryMethod() {
        return true;
    }

    private static final Logger log = LogManager.getLogger(Library.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name;
        String age;
        if((name = resp.getHeader("name")) != null){
            resp.getWriter().println("Welcome, " + name);
        }
        else if((age = req.getParameter("age")) != null){
            resp.getWriter().println("Wow, you're " + age + " years old?");
        }
        else{
        resp.setContentType("application/json");
        resp.getWriter().println("{'message':'Hello there!'}");
        }

        log.debug("Get request received.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);
        log.debug("post request received.");
    }
        
}
