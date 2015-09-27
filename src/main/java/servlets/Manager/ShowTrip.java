package servlets.Manager;

import Model.Entity.Trip;
import Model.Observer.TripObserver;

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
 * Created by Δενθρ on 18.09.2015.
 */
@WebServlet("/show_trips")
public class ShowTrip extends HttpServlet {
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        ArrayList<Trip> trips = TripObserver.select(lang);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < trips.size(); i++) {
            sb.append("<trip>");
            sb.append("<id>" + trips.get(i).getId() + "</id>");
            sb.append("<route_id>" + trips.get(i).getRoute().getId() + "</route_id>");
            sb.append("<from_station_id>" + trips.get(i).getRoute().getFrom().getId() + "</from_station_id>");
            sb.append("<from_station_name>" + trips.get(i).getRoute().getFrom().getName() + "</from_station_name>");
            sb.append("<to_station_id>" + trips.get(i).getRoute().getTo().getId() + "</to_station_id>");
            sb.append("<to_station_name>" + trips.get(i).getRoute().getTo().getName() + "</to_station_name>");
            sb.append("<from_city_id>" + trips.get(i).getRoute().getFrom().getId() + "</from_city_id>");
            sb.append("<from_city_name>" + trips.get(i).getRoute().getFrom().getCity().getName() + "</from_city_name>");
            sb.append("<to_city_id>" + trips.get(i).getRoute().getTo().getId() + "</to_city_id>");
            sb.append("<to_city_name>" + trips.get(i).getRoute().getTo().getCity().getName() + "</to_city_name>");
            sb.append("<bus_name>" + trips.get(i).getBus().getName() + "</bus_name>");
            sb.append("<bus_id>" + trips.get(i).getBus().getId() + "</bus_id>");
            sb.append("<departure_date>" + trips.get(i).getDeparture_date() + "</departure_date>");
            sb.append("<departure_time>" + trips.get(i).getDeparture_time() + "</departure_time>");
            sb.append("<arrival_date>" + trips.get(i).getArrival_date() + "</arrival_date>");
            sb.append("<arrival_time>" + trips.get(i).getArrival_time() + "</arrival_time>");
            sb.append("</trip>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<trips>" + sb.toString() + "</trips>");
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