package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {
    Connection connection;
    Statement statement;

    public DBWorker() {
        DBConnector dbConnector= new DBConnector();
        connection = dbConnector.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: Statement was not created.");
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION in execution query(Select)");
        }
        return  resultSet;
    }

    public void execute(String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("executing query is failed!");
        }
    }

    public int selectIntByString(String table, String column, String param_name, String param_value) {
        int res = 0;
        String query = "SELECT " + column + " FROM " + table + "WHERE " + param_name + " = '" + param_value + "'";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                res = resultSet.getInt(column);
            } else {
                System.out.println("No such data in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: No such data in DB");
        }
        return res;
    }

    public long selectLongByLong(String table, String column, String param_name, long param_value) {
        long res = 0;
        String query = "SELECT " + column + " FROM " + table + " WHERE " + param_name + " = " + param_value;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                res = resultSet.getLong(column);
            } else {
                System.out.println("No such data in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: No such data in DB");
        }
        return res;
    }

    public long selectTripId(long route, long bus, String departureDate, String departureTime, String arrivalDate, String arrivalTime) {
        long res = 0;
        String query = "SELECT Id FROM trips WHERE route = " + route + " AND bus = " + bus + " AND departure = '" + departureDate + " " + departureTime + "' AND arrival = '" + arrivalDate + " " + arrivalTime +"'";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                res = resultSet.getLong(1);
                System.out.println();
            } else {
                System.out.println("No such data in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: No such data in DB");
        }
        return res;
    }

    public String getPhrase(long id, String lang) {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery("SELECT "+ lang +" FROM translator WHERE Id = " + id);
            if(resultSet.next())
                return resultSet.getString(1);
            else
                return "NOT FOUND";
        } catch (SQLException e) {
            e.printStackTrace();
            return "NOT FOUND";
        }
    }



    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: Connection was not closed!");
            e.printStackTrace();
        }
    }
}
