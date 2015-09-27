package servlets.Manager;

import Model.Entity.Bus;
import Model.Observer.BusObserer;

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

@WebServlet("/show_bus")
public class ShowBus extends HttpServlet {

    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        ArrayList<Bus> buses = BusObserer.selectBuses(lang);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < buses.size(); i++) {
            sb.append("<bus>");
            sb.append("<id>" + buses.get(i).getId() + "</id>");
            sb.append("<name>" + buses.get(i).getName() + "</name>");
            sb.append("<seats>" + buses.get(i).getSeats() + "</seats>");
            sb.append("</bus>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<buses>" + sb.toString() + "</buses>");
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
