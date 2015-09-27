package Model.Observer;

import Model.Entity.*;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public abstract class TripObserver {

    public static ArrayList<Trip> select(String lang) {
        ArrayList<Trip> trips = new ArrayList<Trip>();
        DBWorker dbWorker = new DBWorker();
        ResultSet resultSet = null;
        String query = "SELECT c1.name_"+lang+", c1.Id, c2.name_"+lang+", c2.Id, s1.name_"+lang+", s1.Id, s2.name_"+lang+", s2.Id, routes.Id, buses.Id, buses.name_"+lang+", buses.seats, trips.Id, trips.departure, trips.arrival FROM cities c1, cities c2, stations s1, stations s2, routes, trips, buses WHERE\n" +
                "       routes.from_station = s1.Id       \n" +
                "       AND       \n" +
                "       routes.to_station = s2.Id       \n" +
                "       AND       \n" +
                "       s1.city = c1.Id       \n" +
                "       AND       \n" +
                "       s2.city = c2.Id       \n" +
                "       AND       \n" +
                "       trips.route = routes.Id       \n" +
                "       AND       \n" +
                "       trips.bus = buses.Id";

        resultSet = dbWorker.executeQuery(query);

        try {
            while (resultSet.next()) {
                City c1 = new City(resultSet.getLong(2), resultSet.getString(1));
                City c2 = new City(resultSet.getLong(4), resultSet.getString(3));
                Station s1 = new Station(resultSet.getLong(6), c1, resultSet.getString(5));
                Station s2 = new Station(resultSet.getLong(8), c2, resultSet.getString(7));
                Route route = new Route(resultSet.getLong(9), s1, s2);
                Bus bus = new Bus(resultSet.getLong(10), resultSet.getString(11), resultSet.getInt(12));
                Trip trip = new Trip(resultSet.getLong(13), route, bus, resultSet.getDate(14), resultSet.getTime(14), resultSet.getDate(15), resultSet.getTime(15));
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbWorker.closeConnection();
        return trips;
    }

    public static ArrayList<Trip> findTripsByCities(City fromCity, City toCity, String lang) {
        ArrayList<Trip> trips = new ArrayList<Trip>();
        //DBWorker dbWorker = new DBWorker();
        //int fromCityId = fromCity.selectId();
        //int toCityId = toCity.selectId();

        ResultSet resultSet = null;
        String query = "SELECT * FROM trips, routes WHERE\n" +
                "       routes.Id = trips.route       \n" +
                "       AND\n" +
                "       routes.from_station = (SELECT Id FROM cities WHERE name_"+lang+" = '" + fromCity.getName() + "')\n" +
                "       AND\n" +
                "       routes.to_station = (SELECT Id FROM cities WHERE name_"+lang+" = '" + toCity.getName() + "')";
        DBWorker dbWorker = new DBWorker();
        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                //trips.add(new Trip(resultSet.getInt("Id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }
    public static ArrayList<TripViewer> findTripsViewersByCities(City fromCity, City toCity, String lang) {
        ArrayList<TripViewer> tripViewers = new ArrayList<TripViewer>();
        ResultSet resultSet = null;
        String query = "SELECT trips.Id, s1.name_"+lang+", s2.name_"+lang+", trips.departure, trips.arrival FROM trips, routes, stations s1, stations s2 WHERE\n" +
                "       routes.Id = trips.route       \n" +
                "       AND\n" +
                "       routes.from_station = s1.Id\n" +
                "       AND       \n" +
                "       routes.to_station = s2.Id       \n" +
                "       AND\n" +
                "       s1.city = (SELECT Id FROM cities WHERE name = '" + fromCity.getName() + "')\n" +
                "       AND\n" +
                "       s2.city = (SELECT Id FROM cities WHERE name = '" + toCity.getName() + "')";
        DBWorker dbWorker = new DBWorker();
        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                //trips.add(new Trip(resultSet.getInt("Id")));
                tripViewers.add(new TripViewer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getDate(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripViewers;
    }

    public static ArrayList<TripViewer> findTripsViewers(String fromCity, String toCity, String date, String lang) {
        ArrayList<TripViewer> tripViewers = new ArrayList<TripViewer>();
        ResultSet resultSet = null;
        String query = "SELECT trips.Id, s1.name_"+lang+", s2.name_"+lang+", trips.departure, trips.arrival FROM trips, routes, stations s1, stations s2 WHERE\n" +
                "       routes.Id = trips.route       \n" +
                "       AND\n" +
                "       routes.from_station = s1.Id\n" +
                "       AND       \n" +
                "       routes.to_station = s2.Id       \n" +
                "       AND\n" +
                "       s1.city = (SELECT Id FROM cities WHERE name_"+lang+" = '" + fromCity + "')\n" +
                "       AND\n" +
                "       s2.city = (SELECT Id FROM cities WHERE name_"+lang+" = '" + toCity + "')" +
                "       AND\n" +
                "       trips.departure >= '"+ date +"' AND trips.departure < '"+ date +" 23:59:59.997'";
        DBWorker dbWorker = new DBWorker();
        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                //trips.add(new Trip(resultSet.getInt("Id")));
                tripViewers.add(new TripViewer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getTime(4), resultSet.getDate(5), resultSet.getTime(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripViewers;
    }
}