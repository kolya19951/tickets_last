package Model.Manager;

import Model.Observer.SeatObserver;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ����� on 16.09.2015.
 */
public abstract class SeatManager {
    static public void add(long trip, int seat_num, double price, int available) {
        String query = "INSERT INTO seats (trip, seat_num, price, availability) VALUES (" + trip + ", " + seat_num + ", " + price + ", " + available + ")";
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void setGlobalPrice(long trip_id, double price) {
        String query = "UPDATE seats SET price = " + price + " WHERE trip = " + trip_id + " AND (availability = (SELECT Id from seats_status WHERE status = 'free') OR availability = (SELECT Id from seats_status WHERE status = 'reserved by admin'))";
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void update(long id, double price, String availability) {
        String query = "UPDATE seats SET availability = (SELECT Id FROM seats_status WHERE status = '" + availability + "', price = " + price + " WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void updatePrice(long id, double price) {
        if (SeatObserver.isNotSalesOrReserved(id)) {
            String query = "UPDATE seats SET price = " + price + " WHERE Id = " + id;
            DBWorker dbWorker = new DBWorker();
            dbWorker.execute(query);
            dbWorker.closeConnection();
        }
    }

    static public void updateAvailability(final long id, String availability) {
        if (SeatObserver.isNotSalesOrReserved(id)) {
            String query = "UPDATE seats SET availability = (SELECT Id FROM seats_status WHERE status = '" + availability + "') WHERE Id = " + id;
            DBWorker dbWorker = new DBWorker();
            dbWorker.execute(query);
            dbWorker.closeConnection();
            int interval = 3600000; // 1 hours
            Date timeToRun = new Date(System.currentTimeMillis() + interval);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    String query = "SELECT availability FROM seats WHERE Id = " + id;
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
                        query = "UPDATE seats SET availability = (SELECT Id FROM seats_status WHERE status = 'free') WHERE Id = " + id;
                        dbWorker.execute(query);
                    }
                    dbWorker.closeConnection();
                }

            }, timeToRun);
        }
    }

    static public void delete(long id) {
        String query = "DELETE FROM seats WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

}