package servlets;


import Mail.ssl.Sender;
import Model.Entity.Ticket;
import payment.Payment;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by ???? on 16.09.2015.
 */
@WebServlet("/interaction")
public class Interaction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Sender sender = new Sender("tickets.bus@yandex.ua", "610917qwerty");
        request.setCharacterEncoding("UTF-8");
        String ik_co_id = request.getParameter("ik_co_id");
        String ik_co_prs_id = request.getParameter("ik_co_prs_id");
        String ik_inv_id = request.getParameter("ik_inv_id");
        String ik_inv_st = request.getParameter("ik_inv_st");
        String ik_inv_crt = request.getParameter("ik_inv_crt");
        String ik_inv_prc = request.getParameter("ik_inv_prc");
        String ik_trn_id = "ik_trn_id";
        String ik_pm_no = request.getParameter("ik_pm_no");
        String ik_pw_via = request.getParameter("ik_pw_via");
        String ik_am = request.getParameter("ik_am");
        String ik_co_rfn = request.getParameter("ik_co_rfn");
        String ik_ps_price = request.getParameter("ik_ps_price");
        String ik_cur = request.getParameter("ik_cur");
        String ik_desc = request.getParameter("ik_desc");
        String ik_sign = request.getParameter("ik_sign");
        String ik_x_id = request.getParameter("ik_x_id");
        Payment payment = new Payment(ik_pm_no);
        payment.getPay();
        Payment new_payment = new Payment();
        String ip = request.getRemoteAddr();
        new_payment.setParametrs(ip, ik_co_id, ik_co_prs_id, ik_inv_id, ik_inv_st, ik_inv_crt, ik_inv_prc, ik_trn_id, ik_pm_no, ik_pw_via, ik_am, ik_co_rfn, ik_ps_price, ik_cur, ik_desc, ik_sign);
        if (ik_inv_st.equals("success")) {
            payment.insertInDB(ip, ik_co_id, ik_co_prs_id, ik_inv_id, ik_inv_st, ik_inv_crt, ik_inv_prc, ik_trn_id, ik_pm_no, ik_pw_via, ik_am, ik_co_rfn, ik_ps_price, ik_cur, ik_desc, ik_sign);
            Ticket ticket = new Ticket(Long.parseLong(ik_x_id));
            ticket.setStatus((byte) 2);
            String text = payment.getIk_desc() + "Seat num: " + ticket.getSeat_num() + " Client: " + ticket.getClient();
            text = "<html><table align=\"center\" border=\"1px\" cellpadding=\"2\" cellspacing=\"0\" height=\"250px\" width=\"900px\">\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "        <td colspan=\"3\">\n" +
                    "            <center>Boarding document</center>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            Name:\n" +
                    "        </td>\n" +
                    "        <td colspan=\"2\">\n" +
                    "            " +ticket.getClient()+ "\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            Trip:\n" +
                    "        </td>\n" +
                    "        <td height=\"60px\">\n" +
                    "            " + ticket.getFrom() + "\n" +
                    "        </td>\n" +
                    "        <td height=\"60px\">\n" +
                    "            " + ticket.getTo() + "\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            Trip number:\n" +
                    "        </td>\n" +
                    "        <td colspan=\"3\">\n" +
                    "            " + ticket.getSeat().getTrip().getId() + "\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            Departere time:\n" +
                    "        </td>\n" +
                    "        <td colspan=\"3\">\n" +
                    "            " + ticket.getSeat().getTrip().getDeparture_date() + " " + ticket.getSeat().getTrip().getDeparture_time() + "\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            Arrival time:\n" +
                    "        </td>\n" +
                    "        <td colspan=\"3\">\n" +
                    "            " + ticket.getSeat().getTrip().getArrival_date() + " " + ticket.getSeat().getTrip().getArrival_time() + "\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            Seat number:\n" +
                    "        </td>\n" +
                    "        <td colspan=\"2\">\n" +
                    "           " + ticket.getSeat_num() + "\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    </tbody>\n" +
                    "</table></html>";
            sender.send("Ticket from ticket store", text, "tickets.bus@yandex.ua", payment.getEmail());
            sender.send("Ticket from ticket store", text, "tickets.bus@yandex.ua", "kolya.simotyuk@gmail.com");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
