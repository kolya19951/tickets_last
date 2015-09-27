package servlets.Manager;

import Model.Manager.BusManager;
import Model.Manager.LanguageManager;

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
@WebServlet("/lang_manager")
public class LanguageManagerHandler extends HttpServlet {
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

        String name = request.getParameter("lang");

        if (action.equals("add")) {
            LanguageManager.add(name);
        } else if (action.equals("delete")) {
            if(!(name.equals("gb") || name.equals("ru"))) {
                LanguageManager.delete(name);
            }
        } else {
            System.out.println("Illegal action");
        }

        if(lang.equals(name)) {
            session.setAttribute("lang", "gb");
        }
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