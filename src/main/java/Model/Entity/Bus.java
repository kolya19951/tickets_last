package Model.Entity;

/**
 * Created by Δενθρ on 12.09.2015.
 */
public class Bus {
    private long id;
    private String name;
    private int seats;

    public Bus(long id, String name, int seats) {
        this.id = id;
        this.name = name;
        this.seats = seats;
    }

    public Bus(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public long getId() {
        return id;
    }
}
