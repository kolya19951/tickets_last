package Model.Manager;

import Model.Entity.Bus;
import database.DBWorker;

/**
 * Created by ����� on 16.09.2015.
 */
public abstract class BusConfigManager {
    static public void addOneSeat(long bus, long seat, long row, long place) {
        String query = "INSERT INTO bus_config (bus, seat, row, place) VALUES ("+ bus +", "+ seat +", "+ row +", "+ place +")";
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void buildStandardConfig(Bus bus) {
        DBWorker dbWorker = new DBWorker();
        int counter = 1;
        for (int row = 1; row <= bus.getSeats()/4 + 1; row++) {
            for (int place = 1; place <= 2; place++) {
                String query = "INSERT INTO bus_config (bus, seat, row, place) VALUES ("+ bus.getId() +", "+ counter +", "+ row +", "+ place +")";
                dbWorker.execute(query);
                if(++counter > bus.getSeats())
                    break;
            }

            for (int place = 4; place <= 5; place++) {
                String query = "INSERT INTO bus_config (bus, seat, row, place) VALUES ("+ bus.getId() +", "+ counter +", "+ row +", "+ place +")";
                dbWorker.execute(query);
                if (++counter > bus.getSeats())
                    break;
            }
        }
        dbWorker.closeConnection();
    }

    static public void swapSeats(long firstId, long secondId) {
        DBWorker dbWorker = new DBWorker();
        String query = "SELECT @first_row := row, @first_place := place FROM bus_config WHERE Id = "+firstId+" ";
        dbWorker.execute(query);
                query = "SELECT @second_row := row, @second_place := place FROM bus_config WHERE Id = "+secondId+" ";
        dbWorker.execute(query);
                query = "UPDATE bus_config SET row = @second_row, place = @second_place WHERE Id = "+firstId+" ";
        dbWorker.execute(query);
                query = "UPDATE bus_config SET row = @first_row, place = @first_place WHERE Id = "+secondId+" ";
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void replaceSeat(long id, int row, int place) {
        DBWorker dbWorker = new DBWorker();
        String query = "UPDATE bus_config SET row = "+row+", place = "+place+" WHERE Id = "+id;
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }

    static public void delete(long id) {
        String query = "DELETE FROM bus_config WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
}