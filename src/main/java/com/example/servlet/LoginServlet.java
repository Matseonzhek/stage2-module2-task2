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

        if (session.getAttribute("user") != null) {
            req.getRequestDispatcher("user/hello.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        List<String> usersList = Users.getInstance().getUsers();
        if (usersList.contains(login) && !password.isEmpty()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", login);

            req.getRequestDispatcher("user/hello.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
