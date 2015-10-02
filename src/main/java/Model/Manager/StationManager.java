package Model.Manager;

import database.DBWorker;

/**
 * Created by Δενθρ on 12.09.2015.
 */
public abstract class StationManager {
    static public void add(long city, String name, String lang) {
        if (name.equals(""))
            name = "no name";
        if (lang.equals(""))
            lang = "gb";
        //String query = "INSERT INTO buses (name, seats) VALUES ('" + bus.getName() + "', " + bus.getSeats() + ")";
        String query = "INSERT INTO stations (city, name_"+lang+") VALUES ("+ city + ", '"+ name +"')";
        //String query = "SELECT name FROM buses WHERE id = 1";
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
    static public void update(long id, String name, long city, String lang) {
        String query = "UPDATE stations SET city = " + city + ", name_"+lang+"='"+ name +"' WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
    static public void delete(long id) {
        String query = "DELETE FROM stations WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
}
