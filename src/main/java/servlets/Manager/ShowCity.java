package servlets.Manager;

import Model.Entity.City;
import Model.Observer.CitiesObserver;

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
@WebServlet("/show_city")
public class ShowCity extends HttpServlet {
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        ArrayList<City> cities = CitiesObserver.selectCities(lang);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < cities.size(); i++) {
            sb.append("<city>");
            sb.append("<id>" + cities.get(i).getId() + "</id>");
            sb.append("<name>" + cities.get(i).getName() + "</name>");
            sb.append("</city>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<cities>" + sb.toString() + "</cities>");
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
