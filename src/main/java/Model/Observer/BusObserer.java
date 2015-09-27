package Model.Observer;

import Model.Entity.Bus;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Денис on 18.09.2015.
 */
public abstract class BusObserer {
    public static ArrayList<Bus> selectBuses(String lang) {
        ArrayList<Bus> buses = new ArrayList<Bus>();
        ResultSet resultSet = null;
        String query = "SELECT * FROM buses";
        DBWorker dbWorker = new DBWorker();
        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                buses.add(new Bus(resultSet.getLong("id"), resultSet.getString("name_"+lang+""), resultSet.getInt("seats")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

}
