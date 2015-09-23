package servlets.Manager;

import Model.Hash.ComposerData;
import Model.Manager.StationManager;

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
 * Created by ����� on 13.09.2015.
 */
@WebServlet("/station_manager")
public class StationManagerHandler extends HttpServlet {
    private static String str;
    private ServletContext context;
    private ComposerData compData = new ComposerData();
    private HashMap cities = compData.getCities();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "en");
        }
        String lang = (String) session.getAttribute("lang");

        if (action.equals("add")) {
            String name = request.getParameter("name");
            long city = Long.parseLong(request.getParameter("station_city"));
            StationManager.add(city, name, lang);
        } else if (action.equals("delete")) {
            long id = Long.parseLong(request.getParameter("id"));
            StationManager.delete(id);
        } else if (action.equals("edit")) {
            long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            long city = Long.parseLong(request.getParameter("city"));
            StationManager.update(id, name, city, lang);
        } else {
            System.out.println("Illegal action");
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