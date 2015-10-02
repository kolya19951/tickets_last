package servlets;
import Model.Observer.LanguagesObserver;
import database.DBWorker;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ����� on 13.09.2015.
 */
@WebServlet("/autocomplete")
public class Autocomplition extends HttpServlet {
    private static String str;
    private ServletContext context;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String targetId = request.getParameter("name");

        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("lang", "gb");
        }
        String lang = (String) session.getAttribute("lang");
        //Считывание языков
        ArrayList<String> languages = LanguagesObserver.select();

        StringBuffer sb = new StringBuffer();

        boolean namesAdded = false;
        if (action.equals("complete")) {
            if (!targetId.equals("")) {
                DBWorker dbWorker = new DBWorker();
                //String query = "SELECT * FROM cities WHERE name_"+lang+" LIKE '%" + targetId + "%'";
                String l_first = languages.remove(0);
                String query = "SELECT Id, name_"+lang+" FROM cities WHERE name_"+l_first+" LIKE '%" + targetId + "%'";
                for(String l: languages) {
                    query += " OR name_"+l+" LIKE '%" + targetId + "%'";
                }
                ResultSet resultSet = dbWorker.executeQuery(query);

                //sb.append("<action><name>autocomplete</name></action>");
                try {
                    sb.append("<cities>");
                    sb.append("<action><name>autocomplete</name></action>");
                    while (resultSet.next()) {
                        sb.append("<city>");
                        sb.append("<id>" + resultSet.getLong("Id") + "</id>");
                        sb.append("<name>" + resultSet.getString("name_"+lang) + "</name>");
                        sb.append("</city>");
                        namesAdded = true;
                    }
                    sb.append("</cities>");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dbWorker.closeConnection();
            }
            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                String s = sb.toString();
                response.getWriter().write(s);
                System.out.println();
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }
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
