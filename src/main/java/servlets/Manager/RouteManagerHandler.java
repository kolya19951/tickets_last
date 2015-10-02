package servlets.Manager;

import Model.Manager.RouteManager;

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
@WebServlet("/route_manager")
public class RouteManagerHandler extends HttpServlet {
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
            long from = Long.parseLong(request.getParameter("from"));
            long to = Long.parseLong(request.getParameter("to"));
            RouteManager.add(from, to);
        } else if (action.equals("delete")) {
            long id = Long.parseLong(request.getParameter("id"));
            RouteManager.delete(id);
        } else if (action.equals("edit")) {
            long id = Long.parseLong(request.getParameter("id"));
            long from = Long.parseLong(request.getParameter("from"));
            long to = Long.parseLong(request.getParameter("to"));
            RouteManager.update(id, from, to);
        } else {
            System.out.println("Illegal action");
        }
        response.getWriter().write("");
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