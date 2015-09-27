package Model.Manager;

import Model.Entity.Bus;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Δενθρ on 12.09.2015.
 */
public abstract class BusManager {
    static public void add(String name, int seats, String lang) {
        //String query = "INSERT INTO buses (name, seats) VALUES ('" + bus.getName() + "', " + bus.getSeats() + ")";
        String query = "INSERT INTO buses (name_"+ lang +", seats) VALUES ('"+ name +"', "+ seats +")";
        //String query = "SELECT name FROM buses WHERE id = 1";
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        ResultSet resultSet = dbWorker.executeQuery("SELECT LAST_INSERT_ID()");
        long id;
        try {
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (SQLException e) {
            id = 0;
            e.printStackTrace();
        }

        dbWorker.closeConnection();
        BusConfigManager.buildStandardConfig(new Bus(id, name, seats));
    }

    static public void update(long id, String name,  int seats, String lang) {
        String query = "UPDATE buses SET name_"+ lang +" = '" + name + "', seats = "+ seats +" WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void updateName(long id, String name, String lang) {
        String query = "UPDATE buses SET name_"+ lang +" = '" + name + "' WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void updateSeats(long id, int seats) {
        String query = "UPDATE buses SET seats = '" + seats + "' WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
    static public void delete(long id) {
        String query = "DELETE FROM buses WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
}
