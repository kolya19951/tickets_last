package servlets;

import Model.Entity.Seat;
import Model.Entity.Trip;
import Model.Entity.TripViewer;
import Model.Manager.SeatManager;
import Model.Observer.*;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ????? on 14.09.2015.
 */
@WebServlet("/step3")
public class Step3 extends HttpServlet {

    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.isNew())
            session.setAttribute("lang", "gb");
        String lang = (String) session.getAttribute("lang");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.isNew())
            session.setAttribute("lang", "gb");
        String lang = (String) session.getAttribute("lang");
        long id = Long.parseLong(request.getParameter("id"));
        long reserved_seat_id = (Long)session.getAttribute("reserved_seat_id");
        if (id == reserved_seat_id) {
            //session.setAttribute("reserved_seat_id", id);

            DBWorker dbWorker = new DBWorker();

            request.setAttribute("Home", dbWorker.getPhrase(1, lang));
            request.setAttribute("Contact", dbWorker.getPhrase(2, lang));
            request.setAttribute("BuyTicket", dbWorker.getPhrase(3, lang));
            request.setAttribute("Info", dbWorker.getPhrase(4, lang));
            request.setAttribute("Step1", dbWorker.getPhrase(5, lang));
            request.setAttribute("Step2", dbWorker.getPhrase(6, lang));
            request.setAttribute("Step3", dbWorker.getPhrase(7, lang));
            request.setAttribute("Site", dbWorker.getPhrase(16, lang));
            request.setAttribute("Line10", dbWorker.getPhrase(30, lang));
            request.setAttribute("Line11", dbWorker.getPhrase(31, lang));
            request.setAttribute("Line12", dbWorker.getPhrase(32, lang));
            request.setAttribute("Line13", dbWorker.getPhrase(33, lang));
            request.setAttribute("Line14", dbWorker.getPhrase(34, lang));
            request.setAttribute("Line15", dbWorker.getPhrase(35, lang));
            request.setAttribute("Href1", dbWorker.getPhrase(36, lang));
            request.setAttribute("Surname", dbWorker.getPhrase(37, lang));
            request.setAttribute("Name", dbWorker.getPhrase(38, lang));
            request.setAttribute("Phone", dbWorker.getPhrase(39, lang));
            request.setAttribute("Payments", dbWorker.getPhrase(40, lang));
            request.setAttribute("Detals", dbWorker.getPhrase(41, lang));
            request.setAttribute("Help1", dbWorker.getPhrase(42, lang));
            request.setAttribute("Help2", dbWorker.getPhrase(43, lang));
            request.setAttribute("Line16", dbWorker.getPhrase(50, lang));
            request.setAttribute("Date", dbWorker.getPhrase(12, lang));
            request.setAttribute("Departure", dbWorker.getPhrase(13, lang));
            request.setAttribute("Arrival", dbWorker.getPhrase(14, lang));
            request.setAttribute("Price", dbWorker.getPhrase(15, lang));
            request.setAttribute("From", dbWorker.getPhrase(8, lang));
            request.setAttribute("To", dbWorker.getPhrase(9, lang));

            ArrayList<String> languages = LanguagesObserver.select();
            request.setAttribute("languages", languages);

            String query = "SELECT cities1.name_" + lang + ", cities2.name_" + lang + ", stations1.name_" + lang + ", stations2.name_" + lang + ", trips.departure, trips.arrival, seats.price FROM\n" +
                    " cities cities1, cities cities2, stations stations1, stations stations2, trips, seats, routes WHERE \n" +
                    " seats.Id = " + id + "\n" +
                    " AND\n" +
                    " seats.trip = trips.Id \n" +
                    " AND \n" +
                    " trips.route = routes.Id \n" +
                    " AND \n" +
                    " routes.from_station = stations1.Id \n" +
                    " AND \n" +
                    " routes.to_station = stations2.Id \n" +
                    " AND \n" +
                    " stations1.city = cities1.Id \n" +
                    " AND \n" +
                    " stations2.city = cities2.Id";

            ResultSet resultSet = dbWorker.executeQuery(query);
            try {
                if (resultSet.next()) {
                    try {

                        String departureCity = resultSet.getString(1);
                        request.setAttribute("departureCity", departureCity);
                        String arrivalCity = resultSet.getString(2);
                        request.setAttribute("arrivalCity", arrivalCity);
                        String departureStation = resultSet.getString(3);
                        request.setAttribute("departureStation", departureStation);
                        String arrivalStation = resultSet.getString(4);
                        request.setAttribute("arrivalStation", arrivalStation);
                        String departureDate = resultSet.getDate(5).toString();
                        request.setAttribute("departureDate", departureDate);
                        String arrivalDate = resultSet.getDate(6).toString();
                        request.setAttribute("arrivalDate", arrivalDate);
                        String departureTime = resultSet.getTime(5).toString();
                        request.setAttribute("departureTime", departureTime);
                        String arrivalTime = resultSet.getTime(6).toString();
                        request.setAttribute("arrivalTime", arrivalTime);
                        double price = resultSet.getDouble(7);
                        request.setAttribute("price", price);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/step3.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("/reservation");
        }
    }
}