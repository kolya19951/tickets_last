package Model.Observer;

import java.util.ArrayList;

/**
 * Created by ????? on 14.09.2015.
 */
public class SeatPlaceWithPrice {
    private long seatId;
    private int seat_num;
    private int row;
    private int place;
    private double price;
    private String availability;

    public SeatPlaceWithPrice(long seatId, int seat_num, int row, int place, double price, String availability) {
        this.seatId = seatId;
        this.seat_num = seat_num;
        this.row = row;
        this.place = place;
        this.price = price;
        this.availability = availability;
    }


    public SeatPlaceWithPrice(long seatId, int seat_num, int row, int place) {
        this.seatId = seatId;
        this.seat_num = seat_num;
        this.row = row;
        this.place = place;
    }

    public SeatPlaceWithPrice(int seat_num, int row, int place) {
        this.seat_num = seat_num;
        this.row = row;
        this.place = place;
    }

    public long getSeatId() {
        return seatId;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public static SeatViewer getIdXY(ArrayList<SeatPlace> seatPlaces, int row, int place){
        for (SeatPlace item: seatPlaces){
            if (item.getRow() == row && item.getPlace() == place)
                return new SeatViewer(item.getSeatId(), item.getSeat_num());
        }
        return new SeatViewer(0,0);
    }

    public double getPrice() {
        return price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}