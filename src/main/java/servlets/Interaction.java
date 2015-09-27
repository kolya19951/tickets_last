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
        //String ik_trn_id = request.getParameter("ik_trn_id");
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
            sender.send("Ticket from ticket store", payment.getIk_desc(), "tickets.bus@yandex.ua", payment.getEmail());
            Ticket ticket = new Ticket(Long.parseLong(ik_x_id));
            ticket.setStatus((byte)2);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Sender sender = new Sender("tickets.bus@yandex.ua", "610917qwerty");
        request.setCharacterEncoding("UTF-8");
       /* String ik_co_id = request.getParameter("ik_co_id");
        String ik_co_prs_id = request.getParameter("ik_co_prs_id");
        String ik_inv_id = request.getParameter("ik_inv_id");*/
        String ik_inv_st = request.getParameter("ik_inv_st");
        /*String ik_inv_crt = request.getParameter("ik_inv_crt");
        String ik_inv_prc = request.getParameter("ik_inv_prc");
        String ik_trn_id = request.getParameter("ik_trn_id");
        String ik_pm_no = request.getParameter("ik_pm_no");
        String ik_pw_via = request.getParameter("ik_pw_via");
        String ik_am = request.getParameter("ik_am");
        String ik_co_rfn = request.getParameter("ik_co_rfn");
        String ik_ps_price = request.getParameter("ik_ps_price");
        String ik_cur = request.getParameter("ik_cur");
        String ik_desc = request.getParameter("ik_desc");
        String ik_sign = request.getParameter("ik_sign");*/
        String ik_x_id = request.getParameter("ik_x_id");
        //Payment payment = new Payment(ik_pm_no);
        //payment.getPay();
       // Payment new_payment = new Payment();
       // String ip = request.getRemoteAddr();
        //new_payment.setParametrs(ip, ik_co_id, ik_co_prs_id, ik_inv_id, ik_inv_st, ik_inv_crt, ik_inv_prc, ik_trn_id, ik_pm_no, ik_pw_via, ik_am, ik_co_rfn, ik_ps_price, ik_cur, ik_desc, ik_sign);
        if (ik_inv_st.equals("success")) {
            //payment.insertInDB(ip, ik_co_id, ik_co_prs_id, ik_inv_id, ik_inv_st, ik_inv_crt, ik_inv_prc, ik_trn_id, ik_pm_no, ik_pw_via, ik_am, ik_co_rfn, ik_ps_price, ik_cur, ik_desc, ik_sign);
            sender.send("Ticket from ticket store", "desc", "tickets.bus@yandex.ua", "kolya.simotyuk@gmail.com");
            Ticket ticket = new Ticket(Long.parseLong(ik_x_id));
            ticket.setStatus((byte)2);
        }
    }
}
