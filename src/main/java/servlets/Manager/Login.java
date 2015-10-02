package servlets.Manager;

import Model.Manager.CitiesManager;

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

/**
 * Created by Δενθρ on 13.09.2015.
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "en");
        }
        String lang = (String) session.getAttribute("lang");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        if(session.isNew()) {
            session.setAttribute("lang", "en");
        }
        String lang = (String) session.getAttribute("lang");
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    private void handle_action(HttpServletRequest request, HttpServletResponse response) {

    }
}