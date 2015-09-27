package servlets;

import Model.Entity.BusConfigElement;
import Model.Entity.Seat;
import Model.Entity.Trip;
import Model.Entity.TripViewer;
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
import java.util.ArrayList;

/**
 * Created by ????? on 14.09.2015.
 */
@WebServlet("/step2")
public class Step2 extends HttpServlet {

    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        request.setAttribute("Site", dbWorker.getPhrase(16, lang));
        request.setAttribute("NumberPlace", dbWorker.getPhrase(27, lang));
        request.setAttribute("TicketPrice", dbWorker.getPhrase(28, lang));
        request.setAttribute("SelectPrice", dbWorker.getPhrase(29, lang));
        request.setAttribute("Line5", dbWorker.getPhrase(22, lang));
        request.setAttribute("Line6", dbWorker.getPhrase(23, lang));
        request.setAttribute("Line7", dbWorker.getPhrase(24, lang));
        request.setAttribute("Line8", dbWorker.getPhrase(25, lang));
        request.setAttribute("Line9", dbWorker.getPhrase(26, lang));
        request.setAttribute("TextColor1", dbWorker.getPhrase(59, lang));
        request.setAttribute("TextColor2", dbWorker.getPhrase(60, lang));

        ArrayList<String> languages = LanguagesObserver.select();
        request.setAttribute("languages", languages);

        Integer id = new Integer(request.getParameter("id"));
        ArrayList<Seat> seats = SeatObserver.selectSeats(id);
        ArrayList<SeatPlace> seatPlaces = BusConfigObserver.getBusConfigByTrip(id);

        int maxRow = 0;
        for (SeatPlace item : seatPlaces) {
            if (maxRow < item.getRow())
                maxRow = item.getRow();
        }

        Seat[][] indexableSeats = new Seat[5][maxRow];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < maxRow; j++) {
                indexableSeats[i][j] = null;
            }
        }

        for (Seat item : seats) {
            int i = item.getSeat_num();
            for (SeatPlace place: seatPlaces){
                if (i == place.getSeat_num())
                    indexableSeats[place.getPlace()-1][place.getRow()-1] = item;
            }
        }

        /*request.setAttribute("seats", seats);
        request.setAttribute("seatConfig", seatPlaces);*/
        request.setAttribute("indexableSeats", indexableSeats);
        request.setAttribute("maxRow", maxRow);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/step2.jsp");
        dispatcher.forward(request, response);
    }
}