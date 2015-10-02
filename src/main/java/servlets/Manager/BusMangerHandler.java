package servlets.Manager;

import Model.Manager.BusManager;

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
 * Created by ����� on 13.09.2015.
 */
@WebServlet("/bus_manager")
public class BusMangerHandler extends HttpServlet {
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
            String name = request.getParameter("bus_name");
            int seats = Integer.parseInt(request.getParameter("places"));
            BusManager.add(name, seats, lang);
        } else if (action.equals("delete")) {
            long id = Long.parseLong(request.getParameter("id"));
            BusManager.delete(id);
        } else if (action.equals("edit")) {
            long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            int seats = Integer.parseInt(request.getParameter("seats"));
            BusManager.update(id, name, seats, lang);
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
