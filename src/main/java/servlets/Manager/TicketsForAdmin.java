package servlets.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin_tickets")
public class TicketsForAdmin extends HttpServlet {
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
        }
        String lang = (String) session.getAttribute("lang");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_tickets.jsp");
        dispatcher.forward(request, response);
    }
}