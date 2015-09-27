package servlets.Manager;

import Model.Entity.Station;
import Model.Entity.Ticket;
import Model.Observer.StationsObserver;
import Model.Observer.TicketsObserver;
import servlets.Tickets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/show_tickets")
public class ShowTickets extends HttpServlet {

    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        String from_date = request.getParameter("from_date");
        String to_date = request.getParameter("to_date");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String client = request.getParameter("client");
        String seat_num_string = "";
        String seat_num_string_buffer = request.getParameter("seat_num");
        if(seat_num_string_buffer == null) {
            seat_num_string = "";
        } else {
            seat_num_string = seat_num_string_buffer;
        }

        //int seat_num;
        /*if (seat_num_string.equals(""))
            seat_num = 0;
        else seat_num = Integer.parseInt(seat_num_string);*/
        int seat_num = seat_num_string.equals("")?0:Integer.parseInt(seat_num_string);
        String sort_criteria = request.getParameter("sort_criteria");

        ArrayList<Ticket> tickets = TicketsObserver.select(from_date, to_date, from, to, client, seat_num, sort_criteria);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < tickets.size(); i++) {
            sb.append("<ticket>");
            sb.append("<date>" + tickets.get(i).getDate() + "</date>");
            sb.append("<from>" + tickets.get(i).getFrom() + "</from>");
            sb.append("<to>" + tickets.get(i).getTo() + "</to>");
            sb.append("<seat_num>" + tickets.get(i).getSeat_num() + "</seat_num>");
            sb.append("<client>" + tickets.get(i).getClient() + "</client>");
            sb.append("</ticket>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<tickets>" + sb.toString() + "</tickets>");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }
}
