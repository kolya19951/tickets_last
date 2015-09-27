package Model.Observer;

import Model.Entity.City;
import Model.Entity.Station;
import Model.Entity.Ticket;
import database.DBWorker;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Δενθρ on 13.09.2015.
 */
public abstract class TicketsObserver {
    public static ArrayList<Ticket> select(String date_from, String date_to, String from, String to, String client, int seat_num, String sort_criteria) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        DBWorker dbWorker = new DBWorker();
        ResultSet resultSet = null;
        String query;
        if(seat_num != 0)
            query = "SELECT date, from_place, to_place, seat_num, client FROM tickets WHERE (date BETWEEN '"+date_from+"' AND '"+date_to+"') AND (from_place LIKE '%"+from+"%') AND (to_place LIKE '%"+to+"%') AND (client LIKE '%"+client+"%') AND (seat_num = "+seat_num+") ORDER BY " + sort_criteria;
        else
            query = "SELECT date, from_place, to_place, seat_num, client FROM tickets WHERE (date BETWEEN '"+date_from+"' AND '"+date_to+"') AND (from_place LIKE '%"+from+"%') AND (to_place LIKE '%"+to+"%') AND (client LIKE '%"+client+"%') ORDER BY " + sort_criteria;
        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                Ticket ticket = new Ticket(resultSet.getDate("date"), resultSet.getString("client"), resultSet.getString("from_place"), resultSet.getString("to_place"), resultSet.getInt("seat_num"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
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

