package Model.Manager;

import database.DBWorker;

/**
 * Created by Δενθρ on 12.09.2015.
 */
public abstract class TripManager {
    static public void add(long route, long bus, String departureDate, String departureTime, String arrivalDate, String arrivalTime) {
        String query = "INSERT INTO trips (route, bus, departure, arrival) VALUES ("+ route +", "+ bus +", '"+ departureDate +" "+ departureTime +"', '"+ arrivalDate +" "+arrivalTime+"')";
        DBWorker dbWorker = new DBWorker();
        long seatsNumber = dbWorker.selectLongByLong("buses", "seats", "Id", bus);
        dbWorker.execute(query);
        long tripId = dbWorker.selectTripId(route, bus, departureDate, departureTime, arrivalDate, arrivalTime);
        dbWorker.closeConnection();
        for (int i = 0; i < seatsNumber; i++) {
            SeatManager.add(tripId, i + 1, 0.0, 1);
        }
    }

    static public void update(long id, long route, long bus, String departureDate, String departureTime, String arrivalDate, String arrivalTime) {
        String query = "UPDATE trips SET route = "+ route +", bus = "+ bus +", departure = '"+ departureDate +" "+ departureTime +"', arrival = '"+ arrivalDate +" "+arrivalTime+"' WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void delete(long id) {
        DBWorker dbWorker=  new DBWorker();
        String query = "DELETE FROM trips WHERE Id = " + id;
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
}
