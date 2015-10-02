package Model.Manager;

import database.DBWorker;

/**
 * Created by Δενθρ on 16.09.2015.
 */
public abstract class CitiesManager {
    static public void add(String name, String lang) {
        if (name.equals(""))
            name = "no name";
        if (lang.equals(""))
            lang = "gb";
        String query = "INSERT INTO cities (name_"+lang+") VALUES ('"+ name +"')";
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void update(long id, String name, String lang) {
        String query = "UPDATE cities SET name_"+lang+" = '" + name + "' WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
    static public void delete(long id) {
        String query = "DELETE FROM cities WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
}
