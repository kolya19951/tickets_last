package servlets.Manager;

import Model.Entity.Ticket;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/tickets_manager")
public class TicketsManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        long seat_id = Long.parseLong(request.getParameter("seat_id"));
        String client = request.getParameter("client");
        Ticket ticket = new Ticket(seat_id, client);
        long id = ticket.record();
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<tickets><ticket><id>" + id + "</id></ticket></tickets>");
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