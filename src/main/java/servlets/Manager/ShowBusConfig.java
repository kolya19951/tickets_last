package servlets.Manager;

import Model.Entity.BusConfigElement;
import Model.Observer.BusConfigObserver;
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
 * Created by Δενθρ on 18.09.2015.
 */
@WebServlet("/show_bus_config")
public class ShowBusConfig extends HttpServlet {
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        long busId = Long.parseLong(request.getParameter("bus_id"));

        ArrayList<BusConfigElement> busConfigElements = BusConfigObserver.getBusConfig(busId);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < busConfigElements.size(); i++) {
            sb.append("<seat>");
            sb.append("<id>" + busConfigElements.get(i).getId() + "</id>");
            sb.append("<place_num>" + busConfigElements.get(i).getSeat_num() + "</place_num>");
            sb.append("<row>" + busConfigElements.get(i).getRow() + "</row>");
            sb.append("<place>" + busConfigElements.get(i).getPlace() + "</place>");
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