package fakerdata;

import java.sql.*;

public class SQLiteConnect {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:sqlite:/home/codex/Projects/SubjectsDataAutomation/Databases/subject_samples.db";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT * FROM subject_samples";

            Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);

             while (resultSet.next()) {
                 String name = resultSet.getString("name");
                 String school = resultSet.getString("school");
                 String subject = resultSet.getString("subject");
                 String streetAddress = resultSet.getString("streetAddress");

                 System.out.println("full_name: " + name +
                         "school: " + school +
                         " address: " + streetAddress +
                         " subject: " + subject);
             }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database!");
            e.printStackTrace();
        }
    }
}