package servlets;

import Model.Entity.Trip;
import Model.Entity.TripViewer;
import Model.Observer.LanguagesObserver;
import Model.Observer.TripObserver;
import database.DBWorker;

import javax.servlet.RequestDispatcher;
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

/**
 * Created by Денис on 14.09.2015.
 */
@WebServlet("/reservation")
public class SearchTrips extends HttpServlet {
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String date = request.getParameter("date");
        StringBuffer sb = new StringBuffer();
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");


        ArrayList<TripViewer> tripViewers = TripObserver.findTripsViewers(from, to, date, lang);
        for (TripViewer item : tripViewers) {
            sb.append("<trip>");
            sb.append("<datefromtime>" + String.format("%tR", item.getDepartureTime()) + "</datefromtime>");
            sb.append("<datefrom>" + item.getDeparture().toString() + "</datefrom>");
            sb.append("<from>" + item.getFrom() + "</from>");
            sb.append("<datetotime>" + String.format("%tR", item.getArrivalTime()) + "</datetotime>");
            sb.append("<dateto>" + item.getArrival() + "</dateto>");
            sb.append("<triptime>" + (item.getArrival().getTime() - item.getDeparture().getTime())/(60*1000*60) + "</triptime>");
            sb.append("<to>" + item.getTo() + "</to>");
            sb.append("<id>" + item.getId() + "</id>");
            sb.append("<max_price>" + item.getMax_price() + "</max_price>");
            sb.append("<min_price>" + item.getMin_price() + "</min_price>");
            sb.append("</trip>");
        }
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<trips>" + sb.toString() + "</trips>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.isNew())
            session.setAttribute("lang", "gb");
        String lang = (String) session.getAttribute("lang");
        DBWorker dbWorker = new DBWorker();

        request.setAttribute("Home", dbWorker.getPhrase(1, lang));
        request.setAttribute("Contact", dbWorker.getPhrase(2, lang));
        request.setAttribute("BuyTicket", dbWorker.getPhrase(3, lang));
        request.setAttribute("Info", dbWorker.getPhrase(4, lang));
        request.setAttribute("Step1", dbWorker.getPhrase(5, lang));
        request.setAttribute("Step2", dbWorker.getPhrase(6, lang));
        request.setAttribute("Step3", dbWorker.getPhrase(7, lang));
        request.setAttribute("From", dbWorker.getPhrase(8, lang));
        request.setAttribute("To", dbWorker.getPhrase(9, lang));
        request.setAttribute("SelectDate", dbWorker.getPhrase(10, lang));
        request.setAttribute("Search", dbWorker.getPhrase(11, lang));
        request.setAttribute("Date", dbWorker.getPhrase(12, lang));
        request.setAttribute("Departure", dbWorker.getPhrase(13, lang));
        request.setAttribute("Arrival", dbWorker.getPhrase(14, lang));
        request.setAttribute("Price", dbWorker.getPhrase(15, lang));
        request.setAttribute("Site", dbWorker.getPhrase(16, lang));
        request.setAttribute("OneStepLine", dbWorker.getPhrase(17, lang));
        request.setAttribute("Line1", dbWorker.getPhrase(18, lang));
        request.setAttribute("Line2", dbWorker.getPhrase(19, lang));
        request.setAttribute("Line3", dbWorker.getPhrase(20, lang));
        request.setAttribute("Line4", dbWorker.getPhrase(21, lang));

        //request.setAttribute("");
        dbWorker.closeConnection();
        ArrayList<String> languages = LanguagesObserver.select();
        request.setAttribute("languages", languages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/reservation.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }
}


