package Model.Entity;

/**
 * Created by Δενθρ on 14.09.2015.
 */
public class BusConfigElement {
    private long id;
    private Bus bus;
    private int seat_num;
    private int row;
    private int place;

    public BusConfigElement(long id, Bus bus, int seat_num, int row, int place) {
        this.id = id;
        this.bus = bus;
        this.seat_num = seat_num;
        this.row = row;
        this.place = place;
    }

    public BusConfigElement(long id, int seat_num, int row, int place) {
        this.id = id;
        this.seat_num = seat_num;
        this.row = row;
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}