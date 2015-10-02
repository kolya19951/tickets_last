package servlets.Manager;

import Model.Entity.City;
import Model.Entity.Translator;
import Model.Observer.CitiesObserver;
import Model.Observer.TranslatorObserver;

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
@WebServlet("/show_translator")
public class ShowTranslators extends HttpServlet {
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String lang = request.getParameter("lang");

        ArrayList<Translator> translators = TranslatorObserver.select(lang);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < translators.size(); i++) {
            sb.append("<translator>");
            sb.append("<id>" + translators.get(i).getId() + "</id>");
            sb.append("<name>" + translators.get(i).getName() + "</name>");
            sb.append("</translator>");
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<translators>" + sb.toString() + "</translators>");
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

