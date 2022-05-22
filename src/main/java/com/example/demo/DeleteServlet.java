package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.demo.EmployeeRepository.checkDB;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String sid = request.getParameter("id");
            try {
                int id = Integer.parseInt(sid);
                checkDB(id);
                EmployeeRepository.delete(id);
                response.sendRedirect("viewServlet");
            } catch(NumberFormatException e) {
                out.print("Type valid id containing digits only!" + "(Exception: " + e + ")");
            } catch (InvalidIDException e) {
                out.print("Specified ID is not found!" + "(Exception: " + e + ")");
                e.printStackTrace();
            } finally {
                out.close();
            }
    }
}
