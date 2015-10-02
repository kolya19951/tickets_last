package servlets;

import database.DBWorker;
import payment.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ���� on 16.09.2015.
 */
@WebServlet("/newPayment")
public class NewPay extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String ik_co_id = request.getParameter("ik_co_id");
        String ik_pm_no = request.getParameter("ik_pm_no");
        String ik_am = request.getParameter("ik_am");
        String ik_cur = request.getParameter("ik_cur");
        String ik_desc = request.getParameter("ik_desc");
        String action = request.getParameter("action");
        String nameSurname = request.getParameter("name");
        String phone, email;
        Payment payment = new Payment();
        if (action.equals("1")) {
            phone = request.getParameter("phone");
            payment = new Payment(ik_co_id, ik_pm_no, ik_am, ik_cur, ik_desc, nameSurname, 1, phone, "");
        }
        if (action.equals("2")) {
            email = request.getParameter("email");
            payment = new Payment(ik_co_id, ik_pm_no, ik_am, ik_cur, ik_desc, nameSurname, 2, "", email);
        }
        if (action.equals("3")) {
            phone = request.getParameter("phone");
            email = request.getParameter("email");
            payment = new Payment(ik_co_id, ik_pm_no, ik_am, ik_cur, ik_desc, nameSurname, 3, phone, email);
        }

        StringBuffer sb = new StringBuffer();
        long id = payment.getId();
        System.out.println(id);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("paymentId", id);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        sb.append("<ids><id>" + id + "</id></ids>");

        final long reserved_seat_id = (Long) httpSession.getAttribute("reserved_seat_id");

        int interval = 1900000; // 1 hour
        Date timeToRun = new Date(System.currentTimeMillis() + interval);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                String query = "SELECT availability FROM seats WHERE Id = " + reserved_seat_id;
                DBWorker dbWorker = new DBWorker();
                ResultSet resultSet = dbWorker.executeQuery(query);
                int avail = 0;
                try {
                    resultSet.next();
                    avail = resultSet.getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (avail == 3) {
                    query = "UPDATE seats SET availability = (SELECT Id FROM seats_status WHERE status = 'free') WHERE Id = " + reserved_seat_id;
                    dbWorker.execute(query);
                }
                dbWorker.closeConnection();
            }

        }, timeToRun);

        response.getWriter().write("<ids><paymentId><Id>" + id + "</Id></paymentId></ids>");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
