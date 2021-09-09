package fakerdata;

import com.github.javafaker.Faker;

import java.util.Locale;

public class GenerateTestData {

    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("en-ZA"));

        // looping to create data
        for (int i=0; i< 100000; i++){

            String name = faker.name().fullName(); // Miss Samanta Schmidt
            String school = faker.educator().secondarySchool();
            String subject = faker.educator().course();
            String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449

            System.out.println("full_name: " + name +
                    "school: " + school +
                    " address: " + streetAddress +
                    " subject: " + subject);
        }

    }
}
