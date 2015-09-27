package servlets;

import Model.Entity.Trip;
import Model.Entity.TripViewer;
import Model.Observer.TripObserver;

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
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Δενθρ on 14.09.2015.
 */
@WebServlet("/change_language")
public class ChangeLanguage extends HttpServlet {

    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");
        lang = request.getParameter("lang");
        session.setAttribute("lang", lang);
        response.setCharacterEncoding("UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang;
        lang = request.getParameter("lang");
        session.setAttribute("lang", lang);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }
}
