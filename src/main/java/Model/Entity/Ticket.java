package Model.Entity;

import database.DBWorker;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Денис on 12.09.2015.
 */
public class Ticket {
    private long id;
    private Date date;
    private Seat seat;
    private String client;
    private String from;
    private String to;
    private double price;
    private int seat_num;
    private byte status;

    public long record() {
        String query = "INSERT INTO tickets (client, seat_id, price, from_place, to_place, seat_num, date, status) VALUES ('"+client+"', "+seat.getId()+", "+price+", '"+seat.getTrip().getRoute().getFrom().getCity().getName()+", "+seat.getTrip().getRoute().getFrom().getName()+"', '"+seat.getTrip().getRoute().getTo().getCity().getName()+", "+seat.getTrip().getRoute().getTo().getName()+"', "+seat_num+", '"+seat.getTrip().getDeparture_date()+"', 1)";
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        ResultSet resultSet = dbWorker.executeQuery("SELECT LAST_INSERT_ID()");
        long id = 0;
        try {
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Вы ничего не ввели");
        }
        dbWorker.closeConnection();
        return  id;
    }

    public Ticket(long id) {
        this.id = id;
        DBWorker dbWorker = new DBWorker();
        String query = "SELECT seat_id, client FROM tickets WHERE Id = " + id;
        ResultSet resultSet = dbWorker.executeQuery(query);
        long seat_id;
        try {
            resultSet.next();
            this.client = resultSet.getString("client");
            seat_id = resultSet.getLong("seat_id");
            this.seat = new Seat(seat_id);

            date = seat.getTrip().getDeparture_date();
            from = seat.getTrip().getRoute().getFrom().getCity().getName() + ", " + seat.getTrip().getRoute().getFrom().getName();
            to = seat.getTrip().getRoute().getTo().getCity().getName() + ", " + seat.getTrip().getRoute().getTo().getName();
            price = seat.getPrice();
            seat_num = seat.getSeat_num();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbWorker.closeConnection();
    }

    public Ticket(long seat_id, String client) {
        this.seat = new Seat(seat_id);
        this.client = client;
        date = seat.getTrip().getDeparture_date();
        from = seat.getTrip().getRoute().getFrom().getCity().getName() + ", " + seat.getTrip().getRoute().getFrom().getName();
        to = seat.getTrip().getRoute().getTo().getCity().getName() + ", " + seat.getTrip().getRoute().getTo().getName();
        price = seat.getPrice();
        seat_num = seat.getSeat_num();
    }

    public Ticket(Seat seat, String client) {
        this.seat = seat;
        this.client = client;
        date = seat.getTrip().getDeparture_date();
        from = seat.getTrip().getRoute().getFrom().getCity().getName() + ", " + seat.getTrip().getRoute().getFrom().getName();
        to = seat.getTrip().getRoute().getTo().getCity().getName() + ", " + seat.getTrip().getRoute().getTo().getName();
        price = seat.getPrice();
        seat_num = seat.getSeat_num();
    }

    public Ticket(Seat seat, String client, double price) {
        this.seat = seat;
        this.client = client;
        date = seat.getTrip().getDeparture_date();
        from = seat.getTrip().getRoute().getFrom().getCity().getName() + ", " + seat.getTrip().getRoute().getFrom().getName();
        to = seat.getTrip().getRoute().getTo().getCity().getName() + ", " + seat.getTrip().getRoute().getTo().getName();
        price = seat.getPrice();
        seat_num = seat.getSeat_num();
    }

    public Ticket(Date date, String client, String from, String to, int seat_num) {
        this.date = date;
        this.client = client;
        this.from = from;
        this.to = to;
        this.seat_num = seat_num;
    }

    public Ticket(long id, Date date, Seat seat, String client, String from, String to, double price, int seat_num) {
        this.id = id;
        this.date = date;
        this.seat = seat;
        this.client = client;
        this.from = from;
        this.to = to;
        this.price = price;
        this.seat_num = seat_num;
    }

    public Ticket(Date date, Seat seat, String client, String from, String to, double price, int seat_num) {
        this.date = date;
        this.seat = seat;
        this.client = client;
        this.from = from;
        this.to = to;
        this.price = price;
        this.seat_num = seat_num;
    }

    public Ticket(Date date, String client, String from, String to, double price, int seat_num) {
        this.date = date;
        this.client = client;
        this.from = from;
        this.to = to;
        this.price = price;
        this.seat_num = seat_num;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(byte status) {
        DBWorker dbWorker = new DBWorker();
        String query = "UPDATE tickets SET status = " + status + " WHERE Id = " + this.getId();
        dbWorker.execute(query);
        dbWorker.closeConnection();
        this.seat.setAvailability((byte) 2);
        this.status = status;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }
}
