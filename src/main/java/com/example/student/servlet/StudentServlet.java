package com.example.student.servlet;

import com.example.student.dao.StudentDAO;
import com.example.student.model.Student;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private final StudentDAO dao = new StudentDAO();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            List<Student> list = dao.getAllStudents();
            request.setAttribute("students", list);
            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);

        } else if (action.equals("edit")) {

            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Edit requested for ID: " + id);

            Student student = dao.getStudentById(id);

            if (student == null) {
                System.out.println("Student not found!");
            }

            request.setAttribute("student", student);
            request.getRequestDispatcher("edit-student.jsp")
                    .forward(request, response);

        } else if (action.equals("delete")) {

            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteStudent(id);
            response.sendRedirect("students");
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        if (id == null || id.isEmpty()) {
            dao.addStudent(new Student(name, email, course));
        } else {
            dao.updateStudent(
                    new Student(Integer.parseInt(id), name, email, course));
        }

        response.sendRedirect("students");
    }
}