package Model.Entity;

import database.DBWorker;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 * Created by Δενθρ on 12.09.2015.
 */
public class Trip {
    private long id;
    private Route route;
    private Date departure_date;
    private Time departure_time;
    private Date arrival_date;
    private Time arrival_time;
    private Bus bus;
    private double down_price;
    private double upper_price;


    public Trip(long id) {
        this.id = id;
        DBWorker dbWorker = new DBWorker();
        ResultSet resultSet = null;
        String query = "SELECT c1.name_ru, c1.Id, c2.name_ru, c2.Id, s1.name_ru, s1.Id, s2.name_ru, s2.Id, routes.Id, buses.Id, buses.name_ru, buses.seats, trips.Id, trips.departure, trips.arrival FROM cities c1, cities c2, stations s1, stations s2, routes, trips, buses WHERE\n" +
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
                "       trips.bus = buses.Id\n" +
                "       AND \n" +
                "       trips.Id = " + id;

        resultSet = dbWorker.executeQuery(query);

        try {
            resultSet.next();
            City c1 = new City(resultSet.getLong(2), resultSet.getString(1));
            City c2 = new City(resultSet.getLong(4), resultSet.getString(3));
            Station s1 = new Station(resultSet.getLong(6), c1, resultSet.getString(5));
            Station s2 = new Station(resultSet.getLong(8), c2, resultSet.getString(7));
            this.route = new Route(resultSet.getLong(9), s1, s2);
            this.bus = new Bus(resultSet.getLong(10), resultSet.getString(11), resultSet.getInt(12));
            this.departure_date = resultSet.getDate(14);
            this.departure_time = resultSet.getTime(14);
            this.arrival_date = resultSet.getDate(15);
            this.arrival_time = resultSet.getTime(15);
            this.down_price = resultSet.getDouble(16);
            this.upper_price = resultSet.getDouble(17);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbWorker.closeConnection();
    }

    public Trip(long id, Route route, Bus bus, Date departure_date, Time departure_time, Date arrival_date, Time arrival_time) {
        this.id = id;
        this.route = route;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.bus = bus;
    }

    public Trip(long id, Route route, Date departure, Date arrival, Bus bus) {
        this.id = id;
        this.route = route;
        this.departure_date = departure;
        this.arrival_date = arrival;
        this.bus = bus;
    }

    public Trip(Route route, Date departure, Date arrival, Bus bus) {
        this.route = route;
        this.departure_date = departure;
        this.arrival_date = arrival;
        this.bus = bus;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public long getId() {
        return id;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    public Time getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Time arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
