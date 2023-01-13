package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
        } else {
            resp.sendRedirect("user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        List<String> usersList = Users.getInstance().getUsers();
        if (login != null && password != null && !login.isEmpty() && !password.isEmpty()) {
            if (usersList.contains(login)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", login);
                resp.sendRedirect("user/hello.jsp");
            }
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
