package Model.Entity;

import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Коля on 30.09.2015.
 */
public class Translator {
    private long id;
    private String lang;
    private String name;


    public Translator(long id, String lang, String name) {
        this.id = id;
        this.lang = lang;
        this.name = name;
    }

    public Translator(long id, String lang) {
        this.id = id;
        this.lang = lang;
        String query = "SELECT "+lang+" FROM translator WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        ResultSet resultSet = dbWorker.executeQuery(query);
        try {
            if (resultSet.next())
                this.name = resultSet.getString(1);
            else
                this.name = "no name";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbWorker.closeConnection();
    }

    public long getId() {
        return id;
    }

    public String getLang() {
        return lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        String query = "UPDATE translator SET "+ lang + " = '" + name +"' WHERE Id = " + id;
        DBWorker dbWorker = new DBWorker();
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
}
