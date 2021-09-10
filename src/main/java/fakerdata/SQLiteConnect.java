package fakerdata;

import com.github.javafaker.Faker;

import java.sql.*;
import java.util.Locale;

public class SQLiteConnect {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:sqlite:/home/codex/Projects/SubjectsDataAutomation/subject.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();
            String insert = "INSERT INTO fakerTable VALUES (?,?,?,?,?)";

            statement.executeUpdate("DROP TABLE IF EXISTS fakerTable");
            statement.executeUpdate("CREATE TABLE fakerTable (id, name, school, subject, address)");
            statement.executeUpdate("INSERT INTO fakerTable VALUES(1, 'Ms. Devlin Heyns', 'Flowerlake High', 'Associate Degree in Arts',  '69904 Ole Mill' )");
            statement.executeUpdate("INSERT INTO fakerTable VALUES(2, 'Jennifer Masemola', 'Brighthurst Secondary College', 'Master of Commerce',  '86578 Broodryk Stravenue' )");

            ResultSet resultSet = statement.executeQuery("select * from fakerTable");

             while (resultSet.next()) {

                 Faker faker = new Faker(new Locale("en-ZA"));

                 // looping to create data
                 for (int i = 0; i < 10; i++) {

                     Integer id = resultSet.getInt("id");
                     String name = faker.name().fullName();
                     String school = faker.educator().secondarySchool();
                     String subject = faker.educator().course();
                     String streetAddress = faker.address().streetAddress();

                     PreparedStatement pr = connection.prepareStatement(insert);
                     pr.setInt(1,id);
                     pr.setString(2, name);
                     pr.setString(3, school);
                     pr.setString(4, subject);
                     pr.setString(5, streetAddress);

                     System.out.println( id + "   |   " + "name: " + name + "   |   " +
                             " school: " + school + "   |   " +
                             " subject: " + subject +  "   |   " +
                             " address: " + streetAddress);

                     pr.executeUpdate();
                 }
             }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database!");
            e.printStackTrace();
        }
    }
}