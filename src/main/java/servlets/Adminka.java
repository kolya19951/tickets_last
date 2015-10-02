package servlets;

import Model.Observer.LanguagesObserver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Δενθρ on 13.09.2015.
 */

@WebServlet("/adminka")
public class Adminka extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
            session.setAttribute("rights", "user");
        }

        session.getAttributeNames();
        ArrayList<String> languages = LanguagesObserver.select();
        request.setAttribute("languages", languages);
        String lang = (String) session.getAttribute("lang");
        String rights = (String) session.getAttribute("rights");
        if(rights.equals("admin")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminka.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}