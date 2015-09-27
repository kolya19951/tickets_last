package servlets.Manager;

import Model.Manager.TripManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Δενθρ on 13.09.2015.
 */
@WebServlet("/trip_manager")
public class TripManagerHandler extends HttpServlet {
    private static String str;
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        if (action.equals("add")) {
            long route = Long.parseLong(request.getParameter("route"));
            long bus = Long.parseLong(request.getParameter("bus"));
            String departute_date = request.getParameter("departure_date");
            String departure_time = request.getParameter("departure_time");
            String arrival_date = request.getParameter("arrival_date");
            String arrival_time = request.getParameter("arrival_time");
            TripManager.add(route, bus, departute_date, departure_time, arrival_date, arrival_time);
        } else if (action.equals("delete")) {
            long id = Long.parseLong(request.getParameter("id"));
            TripManager.delete(id);
        } else if (action.equals("edit")) {
            long id = Long.parseLong(request.getParameter("id"));
            long route = Long.parseLong(request.getParameter("route"));
            long bus = Long.parseLong(request.getParameter("bus"));
            String departute_date = request.getParameter("departure_date");
            String departure_time = request.getParameter("departure_time");
            String arrival_date = request.getParameter("arrival_date");
            String arrival_time = request.getParameter("arrival_time");
            TripManager.update(id, route, bus, departute_date, departure_time, arrival_date, arrival_time);
        } else {
            System.out.println("Illegal action");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }
}