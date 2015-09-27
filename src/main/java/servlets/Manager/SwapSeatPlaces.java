package servlets.Manager;

import Model.Manager.BusConfigManager;
import database.DBWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ����� on 22.09.2015.
 */
@WebServlet("/swap_seats")
public class SwapSeatPlaces extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("swap")) {
            long firstId = Long.parseLong(req.getParameter("first_id"));
            long secondId = Long.parseLong(req.getParameter("second_id"));
            BusConfigManager.swapSeats(firstId, secondId);
        } else if (action.equals("replace")){
            long id = Long.parseLong(req.getParameter("id"));
            int row = Integer.parseInt(req.getParameter("row"));
            int place = Integer.parseInt(req.getParameter("place"));
            BusConfigManager.replaceSeat(id, row, place);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}