package servlets;

import Mail.SendMailSSL;
import Mail.SendMailTLS;
import Mail.ssl.Sender;
import Model.Entity.Ticket;
import payment.Payment;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ���� on 16.09.2015.
 */
@WebServlet("/success")
public class SuccessResult extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(request.getParameter("id"));
        Ticket ticket = new Ticket(id);
        ticket.setStatus((byte) 2);
        request.setAttribute("Client", ticket.getClient());
        request.setAttribute("From", ticket.getFrom());
        request.setAttribute("To", ticket.getTo());
        request.setAttribute("ID", ticket.getSeat().getTrip().getId());
        request.setAttribute("Seat_Num", ticket.getSeat_num());
        request.setAttribute("From_Date", ticket.getSeat().getTrip().getDeparture_date() + " " + ticket.getSeat().getTrip().getDeparture_time());
        request.setAttribute("To_Date", ticket.getSeat().getTrip().getArrival_date() + " " + ticket.getSeat().getTrip().getArrival_time());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
        dispatcher.forward(request, response);
    }
}
