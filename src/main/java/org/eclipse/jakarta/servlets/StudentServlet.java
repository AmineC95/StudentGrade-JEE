package org.eclipse.jakarta.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.jakarta.model.Student;
import org.eclipse.jakarta.model.Professor;
import org.eclipse.jakarta.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = userService.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String grade = request.getParameter("grade");

        Professor professor = (Professor) request.getSession().getAttribute("professor");

        Student student = new Student();
        student.setName(name);
        student.setGrade(grade);
        student.setProfessor(professor);

        userService.createStudent(student);

        response.sendRedirect(request.getContextPath() + "/students");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String grade = request.getParameter("grade");

        Student student = userService.getStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setGrade(grade);
            userService.updateStudent(student);
        }

        response.sendRedirect(request.getContextPath() + "/students");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Student student = userService.getStudentById(id);
        if (student != null) {
            userService.deleteStudent(student);
        }

        response.sendRedirect(request.getContextPath() + "/students");
    }
}
