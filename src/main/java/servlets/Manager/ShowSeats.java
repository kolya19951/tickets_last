package servlets.Manager;

import Model.Observer.SeatObserver;
import Model.Observer.SeatPlaceWithPrice;

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
 * Created by Денис on 18.09.2015.
 */
@WebServlet("/show_seats")
public class ShowSeats extends HttpServlet {
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        long tripId = Long.parseLong(request.getParameter("tripId"));

//ArrayList<SeatPlaceWithPrice> seatPlaces = BusConfigObserver.busConfig(tripId);
        ArrayList<SeatPlaceWithPrice> seatPlaces = SeatObserver.selectSeatsWithPrice(tripId);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < seatPlaces.size(); i++) {
            sb.append("<seat>");
            sb.append("<id>" + seatPlaces.get(i).getSeatId() + "</id>");
            sb.append("<place_num>" + seatPlaces.get(i).getSeat_num() + "</place_num>");
            sb.append("<price>" + seatPlaces.get(i).getPrice() + "</price>");
            sb.append("<availability>" + seatPlaces.get(i).getAvailability() + "</availability>");
            sb.append("<row>" + seatPlaces.get(i).getRow() + "</row>");
            sb.append("<place>" + seatPlaces.get(i).getPlace() + "</place>");
            sb.append("</seat>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<seats>" + sb.toString() + "</seats>");
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