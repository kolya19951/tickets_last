package Model.Manager;

import Model.Observer.SeatObserver;
import database.DBWorker;

/**
 * Created by Δενθρ on 16.09.2015.
 */
public abstract class SeatManager {
    static public void add(long trip, int seat_num, double price, int available) {
        String query = "INSERT INTO seats (trip, seat_num, price, availability) VALUES (" + trip + ", " + seat_num + ", " + price + ", " + available + ")";
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void setGlobalPrice(long trip_id, double price) {
        String query = "UPDATE seats SET price = " + price + " WHERE trip = " + trip_id;
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

    static public void updateAvailability(long id, String availability) {
        if (SeatObserver.isNotSalesOrReserved(id)) {
            String query = "UPDATE seats SET availability = (SELECT Id FROM seats_status WHERE status = '" + availability + "') WHERE Id = " + id;
            DBWorker dbWorker = new DBWorker();
            dbWorker.execute(query);
            dbWorker.closeConnection();
        }
    }

    static public void delete(long id) {
        String query = "DELETE FROM seats WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

}