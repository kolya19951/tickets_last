package Model.Observer;

import Model.Entity.Bus;
import Model.Entity.Translator;
import database.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Δενθρ on 18.09.2015.
 */
public abstract class TranslatorObserver {
    public static ArrayList<Translator> select(String lang) {
        ArrayList<Translator> translators = new ArrayList<Translator>();
        ResultSet resultSet = null;
        String query = "SELECT Id, "+lang+" FROM translator";
        DBWorker dbWorker = new DBWorker();
        resultSet = dbWorker.executeQuery(query);
        try {
            while (resultSet.next()) {
                translators.add(new Translator(resultSet.getLong("Id"), lang, resultSet.getString("" + lang + "")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return translators;
    }

}
