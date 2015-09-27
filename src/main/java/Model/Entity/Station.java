package Model.Entity;

/**
 * Created by Δενθρ on 12.09.2015.
 */
public class Station {
    private long id;
    private City city;
    private String name;

    public Station(long id, City city, String name) {
        this.id = id;
        this.city = city;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Station(City city, String name) {
        this.city = city;
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
