package fakerdata;

import com.github.javafaker.Faker;

import java.sql.*;
import java.util.Locale;

public class SQLiteConnect {

    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/codex/Projects/SubjectsDataAutomation/subject.db");
            Statement statement = connection.createStatement();
            String insert = "INSERT INTO fakerTable VALUES (?,?,?,?,?)";

            statement.executeUpdate("DROP TABLE IF EXISTS fakerTable");
            statement.executeUpdate("CREATE TABLE fakerTable (id INTEGER PRIMARY KEY, name STRING, school STRING, subject STRING, address STRING)");
            statement.executeUpdate("INSERT INTO fakerTable VALUES(1, 'Ms. Devlin Heyns', 'Flowerlake High', 'Associate Degree in Arts',  '69904 Ole Mill' )");


            ResultSet resultSet = statement.executeQuery("select * from fakerTable");
            Faker faker = new Faker(new Locale("en-ZA"));

                 // looping to create data
                 for (int i = 1; i <= 100000; i++) {

                     Integer id = resultSet.getInt("id");
                     String name = faker.name().fullName();
                     String school = faker.educator().secondarySchool();
                     String subject = faker.educator().course();
                     String streetAddress = faker.address().streetAddress();

                     PreparedStatement pr = connection.prepareStatement(insert);
//                     pr.setInt(1,1);
                     pr.setString(2, name);
                     pr.setString(3, school);
                     pr.setString(4, subject);
                     pr.setString(5, streetAddress);

                     System.out.println("name: " + name + "   |   " +
                             " school: " + school + "   |   " +
                             " subject: " + subject +  "   |   " +
                             " address: " + streetAddress);

                     pr.executeUpdate();
                 }

        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database!");
            e.printStackTrace();
        }
    }
}