package servlets;

import Mail.ssl.Sender;
import Model.Observer.LanguagesObserver;
import database.DBWorker;

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
 * Created by Денис on 13.09.2015.
 */
@WebServlet("/contacts")
public class Contacts extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.isNew())
            session.setAttribute("lang", "gb");
        String lang = (String) session.getAttribute("lang");
        DBWorker dbWorker = new DBWorker();

        request.setAttribute("Home", dbWorker.getPhrase(1, lang));
        request.setAttribute("Contact", dbWorker.getPhrase(2, lang));
        request.setAttribute("BuyTicket", dbWorker.getPhrase(3, lang));
        request.setAttribute("Info", dbWorker.getPhrase(4, lang));
        request.setAttribute("Site", dbWorker.getPhrase(16, lang));
        request.setAttribute("Name", dbWorker.getPhrase(38, lang));
        request.setAttribute("Message", dbWorker.getPhrase(44, lang));
        request.setAttribute("Send", dbWorker.getPhrase(45, lang));

        dbWorker.closeConnection();

        Sender sender = new Sender("tickets.bus@yandex.ua", "610917qwerty");
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String message = request.getParameter("message");
        sender.send("from client", "From " + name + " (" + email + "), " + message, "tickets.bus@yandex.ua", "kolya.simotyuk@gmail.com");
        String success = "Email sent";
        request.setAttribute("result", success);



        ArrayList<String> languages = LanguagesObserver.select();
        request.setAttribute("languages", languages);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/contacts.jsp");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        if(session.isNew())
            session.setAttribute("lang", "gb");
        String lang = (String) session.getAttribute("lang");
        DBWorker dbWorker = new DBWorker();

        request.setAttribute("Home", dbWorker.getPhrase(1, lang));
        request.setAttribute("Contact", dbWorker.getPhrase(2, lang));
        request.setAttribute("BuyTicket", dbWorker.getPhrase(3, lang));
        request.setAttribute("Info", dbWorker.getPhrase(4, lang));
        request.setAttribute("Site", dbWorker.getPhrase(16, lang));
        request.setAttribute("Name", dbWorker.getPhrase(38, lang));
        request.setAttribute("Message", dbWorker.getPhrase(44, lang));
        request.setAttribute("Send", dbWorker.getPhrase(45, lang));

        ArrayList<String> languages = LanguagesObserver.select();
        request.setAttribute("languages", languages);

        dbWorker.closeConnection();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/contacts.jsp");
        dispatcher.forward(request, response);
    }
}
