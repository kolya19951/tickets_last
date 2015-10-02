package Model.Observer;

import Model.Entity.City;
import Model.Entity.Station;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Δενθρ on 13.09.2015.
 */
public abstract class StationsObserver {
    public static ArrayList<Station> select(String lang) {
        ArrayList<Station> stations = new ArrayList<Station>();
        DBWorker dbWorker = new DBWorker();
        ResultSet resultSet = null;
        String query = "SELECT cities.name_"+lang+", cities.Id, stations.name_"+lang+", stations.Id FROM cities, stations WHERE stations.city = cities.Id";

        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                City city = new City(resultSet.getLong(2), resultSet.getString(1));
                Station station = new Station(resultSet.getLong(4), city, resultSet.getString(3));
                stations.add(station);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }

    public static ArrayList<Station> selectByCity(long city_id, String lang) {
        ArrayList<Station> stations = new ArrayList<Station>();
        DBWorker dbWorker = new DBWorker();
        ResultSet resultSet = null;
        String query = "SELECT cities.name_"+lang+", cities.Id, stations.name_"+lang+", stations.Id FROM cities, stations WHERE stations.city = cities.Id AND cities.Id = "+ city_id;

        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                City city = new City(resultSet.getLong(2), resultSet.getString(1));
                Station station = new Station(resultSet.getLong(4), city, resultSet.getString(3));
                stations.add(station);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }

    public static ArrayList<Station> selectWithFilter(String lang, String filter) {
        ArrayList<Station> stations = new ArrayList<Station>();
        DBWorker dbWorker = new DBWorker();
        ResultSet resultSet = null;
        String query = "SELECT cities.name_"+lang+", cities.Id, stations.name_"+lang+", stations.Id FROM cities, stations WHERE stations.city = cities.Id AND cities.name_"+lang+" LIKE '%"+ filter + "%'";

        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                City city = new City(resultSet.getLong(2), resultSet.getString(1));
                Station station = new Station(resultSet.getLong(4), city, resultSet.getString(3));
                stations.add(station);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
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
