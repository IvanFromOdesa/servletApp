package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.demo.EmployeeRepository.checkDB;

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String sid = request.getParameter("id");

            try {
                int id = Integer.parseInt(sid);
                checkDB(id);
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String experience = request.getParameter("experience");
                String salary = request.getParameter("salary");
                String working_hours = request.getParameter("working_hours");

                Employee employee = new Employee();
                employee.setId(id);
                employee.setName(name);
                employee.setEmail(email);
                employee.setCountry(request.getParameter("country"));
                employee.setExperience(experience);
                employee.setSalary(salary);
                employee.setWorking_hours(working_hours);

                int status = EmployeeRepository.update(employee);

                if (status > 0) {
                    response.sendRedirect("viewServlet");
                } else {
                    out.println("Sorry! unable to update record");
                }
            } catch (NumberFormatException e) {
                out.print("Type valid id containing digits only!" + "(Exception: " + e + ")");
            } catch (InvalidIDException e) {
                out.print("Specified ID is not found!" + "(Exception: " + e + ")");
                e.printStackTrace();
            }
            finally {
                out.close();
            }
    }
}
