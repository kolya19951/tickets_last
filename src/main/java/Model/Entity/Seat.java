package Model.Entity;

import database.DBConnector;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Δενθρ on 12.09.2015.
 */
public class Seat {
    private long Id;
    private Trip trip;
    private int seat_num;
    private double price;
    private byte availability;

    public Seat(long id) {
        Id = id;
        DBWorker dbWorker = new DBWorker();
        ResultSet resultSet = null;
        String query = "SELECT c1.name_ru, c1.Id, c2.name_ru, c2.Id, s1.name_ru, s1.Id, s2.name_ru, s2.Id, routes.Id, buses.Id, buses.name_ru, buses.seats, trips.Id, trips.departure, trips.arrival, seats.seat_num, seats.price, seats.availability FROM cities c1, cities c2, stations s1, stations s2, routes, trips, buses, seats WHERE\n" +
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
                "       seats.trip = trips.Id \n" +
                "       AND \n" +
                "       seats.Id = " + id;

        resultSet = dbWorker.executeQuery(query);

        try {
            resultSet.next();
            City c1 = new City(resultSet.getLong(2), resultSet.getString(1));
            City c2 = new City(resultSet.getLong(4), resultSet.getString(3));
            Station s1 = new Station(resultSet.getLong(6), c1, resultSet.getString(5));
            Station s2 = new Station(resultSet.getLong(8), c2, resultSet.getString(7));
            Route route = new Route(resultSet.getLong(9), s1, s2);
            Bus bus = new Bus(resultSet.getLong(10), resultSet.getString(11), resultSet.getInt(12));
            Trip trip = new Trip(resultSet.getLong(13), route, bus, resultSet.getDate(14), resultSet.getTime(14), resultSet.getDate(15), resultSet.getTime(15));
            this.trip = trip;
            seat_num = resultSet.getInt(16);
            price = resultSet.getDouble(17);
            availability = resultSet.getByte(18);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbWorker.closeConnection();
    }

    public Seat(long trip_id, int seat_num, double price, byte availability) {
        this.trip = new Trip(trip_id);
        this.seat_num = seat_num;
        this.price = price;
        this.availability = availability;
    }

    public Seat(long id, Trip trip, int seat_num, double price, byte availability) {
        Id = id;
        this.trip = trip;
        this.seat_num = seat_num;
        this.price = price;
        this.availability = availability;
    }

    public Seat(long id, long trip_id, int seat_num, double price, byte availability) {
        Id = id;
        this.trip = new Trip(trip_id);
        this.seat_num = seat_num;
        this.price = price;
        this.availability = availability;
    }

    public long getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTripId(Trip trip) {
        this.trip = trip;
    }

    public Seat(Trip trip, int seat_num, double price, byte availability) {
        this.trip = trip;
        this.seat_num = seat_num;
        this.price = price;
        this.availability = availability;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(byte availability) {
        DBWorker dbWorker = new DBWorker();
        String query = "UPDATE seats SET availability = " + availability;
        dbWorker.closeConnection();
        this.availability = availability;
    }
}
