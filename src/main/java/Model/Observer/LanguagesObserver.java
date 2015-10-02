package Model.Observer;

import Model.Entity.City;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Δενθρ on 13.09.2015.
 */
public abstract class LanguagesObserver {
    public static ArrayList<String> select() {
        ArrayList<String> languages = new ArrayList<String>();
        ResultSet resultSet = null;
        String query = "SELECT lang FROM languages";
        DBWorker dbWorker = new DBWorker();
        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                languages.add(resultSet.getString("lang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbWorker.closeConnection();
        return languages;

    }

    public static ArrayList<String> selectCitiesNames(String lang) {
        ArrayList<String> cities = new ArrayList<String>();
        ResultSet resultSet = null;
        String query = "SELECT name FROM cities";
        DBWorker dbWorker = new DBWorker();
        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                cities.add(new City(resultSet.getString("name_"+lang+"")).getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
