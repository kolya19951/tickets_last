package servlets;

import Model.Entity.TripViewer;
import Model.Observer.CitiesObserver;
import Model.Observer.TripObserver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Коля on 29.09.2015.
 */
@WebServlet("/unsuccess")
public class UnsuccessResult {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/unsuccess.jsp");
        dispatcher.forward(request, response);
    }
}
