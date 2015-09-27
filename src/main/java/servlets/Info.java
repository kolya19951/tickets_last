package servlets;

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
@WebServlet("/info")
public class Info extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        request.setAttribute("Line17", dbWorker.getPhrase(47, lang));
        request.setAttribute("Line18", dbWorker.getPhrase(48, lang));
        request.setAttribute("Line19", dbWorker.getPhrase(49, lang));
        request.setAttribute("InfoDriver", dbWorker.getPhrase(51, lang));
        request.setAttribute("InfoPass", dbWorker.getPhrase(52, lang));
        request.setAttribute("Rules", dbWorker.getPhrase(53, lang));
        request.setAttribute("ApplyJob", dbWorker.getPhrase(54, lang));
        request.setAttribute("Documents", dbWorker.getPhrase(55, lang));
        request.setAttribute("Requiraments1", dbWorker.getPhrase(56, lang));
        request.setAttribute("Requiraments2", dbWorker.getPhrase(57, lang));
        request.setAttribute("Requiraments3", dbWorker.getPhrase(58, lang));

        ArrayList<String> languages = LanguagesObserver.select();
        request.setAttribute("languages", languages);

        dbWorker.closeConnection();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/info.jsp");
        dispatcher.forward(request, response);
    }
}
