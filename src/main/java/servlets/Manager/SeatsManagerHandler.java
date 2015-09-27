package servlets.Manager;

import Model.Manager.SeatManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Δενθρ on 13.09.2015.
 */
@WebServlet("/seats_manager")
public class SeatsManagerHandler extends HttpServlet {
    private static String str;
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "en");
        }
        String lang = (String) session.getAttribute("lang");

        if (action.equals("update")) {
            long seat_id = Long.parseLong(request.getParameter("seat_id"));
            String availability = request.getParameter("availability");
            if (availability.equals("free") || availability.equals("reserved by admin"))
                SeatManager.updateAvailability(seat_id, availability);
            Double price = Double.parseDouble(request.getParameter("price"));
            SeatManager.updatePrice(seat_id, price);
        } else if (action.equals("set_global_price")) {
            long trip_id = Long.parseLong(request.getParameter("trip_id"));
            double price = Double.parseDouble(request.getParameter("price"));
            SeatManager.setGlobalPrice(trip_id, price);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "en");
        }
        String lang = (String) session.getAttribute("lang");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }
}