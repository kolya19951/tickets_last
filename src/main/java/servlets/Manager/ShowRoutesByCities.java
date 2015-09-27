package servlets.Manager;

import Model.Entity.Route;
import Model.Observer.RoutesObserver;

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
@WebServlet("/show_route_by_cities")
public class ShowRoutesByCities extends HttpServlet {
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");
        long from = Long.parseLong(request.getParameter("from"));
        long to = Long.parseLong(request.getParameter("to"));

        ArrayList<Route> routes = RoutesObserver.selectRoutesByCities(from, to, lang);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < routes.size(); i++) {
            sb.append("<route>");
            sb.append("<id>" + routes.get(i).getId() + "</id>");
            sb.append("<from_station_id>" + routes.get(i).getFrom().getId() + "</from_station_id>");
            sb.append("<from_station_name>" + routes.get(i).getFrom().getName() + "</from_station_name>");
            sb.append("<to_station_id>" + routes.get(i).getTo().getId() + "</to_station_id>");
            sb.append("<to_station_name>" + routes.get(i).getTo().getName() + "</to_station_name>");
            sb.append("<from_city_id>" + routes.get(i).getFrom().getCity().getId() + "</from_city_id>");
            sb.append("<from_city_name>" + routes.get(i).getFrom().getCity().getName() + "</from_city_name>");
            sb.append("<to_city_id>" + routes.get(i).getTo().getCity().getId() + "</to_city_id>");
            sb.append("<to_city_name>" + routes.get(i).getTo().getCity().getName() + "</to_city_name>");
            sb.append("</route>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<routes>" + sb.toString() + "</routes>");
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
