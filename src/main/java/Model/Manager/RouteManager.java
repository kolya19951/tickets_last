package Model.Manager;

import database.DBWorker;

/**
 * Created by Δενθρ on 12.09.2015.
 */
public abstract class RouteManager {
    static public void add(long from, long to) {
        String query = "INSERT INTO routes (from_station, to_station) VALUES ("+ from +", "+ to +")";
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void update(long id, long from, long to) {
        String query = "UPDATE routes SET from_station = " + from + ", to_station = " + to +" WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void delete(long id) {
        String query = "DELETE FROM routes WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
}
