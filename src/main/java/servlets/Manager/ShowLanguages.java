package servlets.Manager;

import Model.Observer.LanguagesObserver;

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
@WebServlet("/show_languages")
public class ShowLanguages extends HttpServlet {
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");

        ArrayList<String> languages = LanguagesObserver.select();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < languages.size(); i++) {
            sb.append("<lang>");
            sb.append("<name>" + languages.get(i) + "</name>");
            sb.append("</lang>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<languages>" + sb.toString() + "</languages>");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");
    }

    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }
}