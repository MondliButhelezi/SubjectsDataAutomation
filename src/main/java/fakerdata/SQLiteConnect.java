package fakerdata;

import java.sql.*;

public class SQLiteConnect {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:sqlite:/home/codex/Projects/SubjectsDataAutomation/src/main/resources/subject.db";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT * FROM fakerTable";

            Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);

             while (resultSet.next()) {
                 String name = resultSet.getString("name");
                 String school = resultSet.getString("school");
                 String subject = resultSet.getString("subject.db");
                 String streetAddress = resultSet.getString("streetAddress");

                 System.out.println("full_name: " + name +
                         "school: " + school +
                         " address: " + streetAddress +
                         " subject.db: " + subject);
             }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database!");
            e.printStackTrace();
        }
    }
}