package com.example.demo.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log(">>> AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        this.context.log("AuthenticationFilter started processing!");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        this.context.log("Requested Resource::http://localhost:8080" + uri);

        HttpSession session = req.getSession(false);

        PrintWriter out = res.getWriter();
        String password = req.getParameter("password");
        Boolean access = true;
        if(!password.equals("mypassword")){
            access=false;
            out.print("Invalid password!");
        }

        if (session == null && (!access || !(uri.endsWith("demo/saveServlet") || uri.endsWith("demo/viewByIDServlet") || uri.endsWith("demo/viewServlet") || uri.endsWith("demo/putServlet") || uri.endsWith("demo/deleteServlet")))) {
            this.context.log("<<< Unauthorized access request");
            out.println("No access!!!");
        } else {
            this.context.log("AuthenticationFilter passed!");
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        //close any resources here
    }
}
