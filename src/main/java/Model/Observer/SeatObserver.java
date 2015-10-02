package Model.Observer;

import Model.Entity.Seat;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Денис on 12.09.2015.
 */
public abstract class SeatObserver {
    public static ArrayList<Seat> selectSeats(long tripId) {
        ArrayList<Seat> seats = new ArrayList<Seat>();
        DBWorker dbWorker = new DBWorker();
        ResultSet resultSet = dbWorker.executeQuery("SELECT * FROM seats WHERE trip = " + tripId);
        try {
            while (resultSet.next()) {
                seats.add(new Seat(resultSet.getLong("Id"), tripId, resultSet.getInt("seat_num"), resultSet.getDouble("price"), resultSet.getByte("availability")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    public static ArrayList<SeatPlaceWithPrice> selectSeatsWithPrice(long tripId) {
        ArrayList<SeatPlaceWithPrice> res = new ArrayList<SeatPlaceWithPrice>();
        ResultSet resultSet = null;
        String query = "SELECT DISTINCT seats.Id, seats.seat_num, seats.price, seats_status.status, bus_config.row, bus_config.place FROM bus_config, seats, trips, seats_status WHERE bus_config.bus = trips.bus " +
                "AND bus_config.seat = seats.seat_num " +
                "AND trips.Id = " + tripId +
                " AND seats.trip = " + tripId +
                " AND seats_status.Id = seats.availability ORDER BY seats.seat_num";
        DBWorker dbWorker = new DBWorker();
        resultSet = dbWorker.executeQuery(query);

        try {
            while (resultSet.next()) {
                res.add(new SeatPlaceWithPrice(resultSet.getLong(1), resultSet.getInt(2), resultSet.getInt(5), resultSet.getInt(6), resultSet.getDouble(3), resultSet.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbWorker.closeConnection();
        return res;
    }

    public static boolean isFree(long id) {
        DBWorker dbWorker = new DBWorker();
        String query = "SELECT status FROM seats_status WHERE Id = (SELECT availability FROM seats WHERE Id = " + id + ")";
        ResultSet resultSet = dbWorker.executeQuery(query);
        try {
            resultSet.next();
            String status = resultSet.getString(1);
            if (status.equals("free")) {
                return true;
            } else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isNotSalesOrReserved(long id) {
        DBWorker dbWorker = new DBWorker();
        String query = "SELECT status FROM seats_status WHERE Id = (SELECT availability FROM seats WHERE Id = "+id + ")";
        ResultSet resultSet = dbWorker.executeQuery(query);
        try {
            resultSet.next();
            String status = resultSet.getString(1);
            if(status.equals("free") || status.equals("reserved by admin")) {
                return true;
            } else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
