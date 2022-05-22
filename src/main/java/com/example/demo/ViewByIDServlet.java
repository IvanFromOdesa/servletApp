package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.demo.EmployeeRepository.checkDB;


@WebServlet("/viewByIDServlet")
public class ViewByIDServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");

            try {
                int id = Integer.parseInt(sid);
                checkDB(id);
                Employee employee = EmployeeRepository.getEmployeeById(id);
                out.print(employee);
            } catch (NumberFormatException e) {
                out.print("Type valid id containing digits only!" + "(Exception: " + e + ")");}
              catch (InvalidIDException e) {
                out.print("Specified ID is not found!" + "(Exception: " + e + ")");
                e.printStackTrace();
            } finally{
                out.close();
            }
    }
}
