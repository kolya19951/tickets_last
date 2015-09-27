package Model.Manager;

import Model.Entity.Bus;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Δενθρ on 12.09.2015.
 */
public abstract class LanguageManager {
    static public void add(String name) {
        DBWorker dbWorker = new DBWorker();
        if (name.length()!=2)
            return;
        String query = "INSERT INTO languages (lang) VALUES ('"+name+"')";
        dbWorker.execute(query);
        query = "ALTER TABLE buses ADD name_"+name+" VARCHAR(255)";
        dbWorker.execute(query);
        query = "ALTER TABLE cities ADD name_"+name+" VARCHAR(255)";
        dbWorker.execute(query);
        query = "ALTER TABLE stations ADD name_"+name+" VARCHAR(255)";
        dbWorker.execute(query);
        query = "ALTER TABLE translator ADD "+name+" VARCHAR(255)";
        dbWorker.execute(query);
        ResultSet resultSet = dbWorker.executeQuery("SELECT LAST_INSERT_ID()");
        dbWorker.closeConnection();
    }

    static public void delete(String name) {
        DBWorker dbWorker = new DBWorker();
        String query = "DELETE FROM languages WHERE lang = '"+name+"'";
        dbWorker.execute(query);
        query = "ALTER TABLE buses DROP name_"+name;
        dbWorker.execute(query);
        query = "ALTER TABLE cities DROP name_"+name;
        dbWorker.execute(query);
        query = "ALTER TABLE stations DROP name_"+name;
        dbWorker.execute(query);
        query = "ALTER TABLE translator DROP "+name;
        dbWorker.execute(query);
        dbWorker.closeConnection();
    }
}