package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Lionel", "Messi", 32, 50961120, 54321);
        Person Joe = new Person("Joe", "Calak", 24, 12345678, 12199);
        Person Beethoven = new Person("Beethoven", "Marhone", 22, 12456745, 12347);
        Person Alexis = new Person("Alexis", "Frandy", 27, 78976734, 12348);
        Person Kurt = new Person("Kurt", "Mack", 16, 5136116, 12349);
        Person Suarez = new Person("Denis", "Suarez", 26, 5136117, 34576);

        createTable();
        insertPerson(person);
        insertPerson(Joe);
        insertPerson(Beethoven);
        insertPerson(Alexis);
        insertPerson(Kurt);
        insertPerson(Suarez);

        System.out.println(selectPerson(4));
        for (Person p : findAllPeople()) {
            System.out.println(p);
        }
        deletePerson(3);

        for (Person p : findAllPeople()) {
            System.out.println(p);
        }

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/bmarhone/IdeaProjects/Database/javatest.db");
            System.out.println(" database is opened successfully");
        } catch (Exception e) {
            System.out.println(e);
            connection = null;
        }

        return connection;
    }

    public static Connection createTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();

            statement = connection.createStatement();
            String sql = "CREATE TABLE PLACE " + "(ID INTEGER PRIMARY KEY        AUTOINCREMENT, "
                    + "FIRSTNAME           CHAR(40)   NOT NULL, " + "LASTNAME            CHAR(40)   NOT NULL, "
                    + "AGE                 INT                , " + "CREDITCARD          LONG             , "
                    + "SSN                 LONG             ) ";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();

            System.out.println("Table is successfully created ");
        } catch (Exception e) {
            System.out.println(e);
            connection = null;
        }

        return connection;
    }

    public static Connection insertPerson(Person person) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            String sql = "INSERT INTO PLACE (FIRSTNAME,LASTNAME,AGE,CREDITCARD,SSN) " + "VALUES (" + "'"
                    + person.getFirstName() + "'," + "'" + person.getLastName() + "'," + person.getAge() + ","
                    + person.getCreditCard() + "," + person.getSsn() + " );";
            statement.executeUpdate(sql);

            statement.close();
            connection.commit();
            connection.close();

            System.out.println("(Insert Person " + person.getFirstName() + " " + person.getLastName()
                    + ") is successfully inserted");
        } catch (Exception e) {
            System.out.println(e);
            connection = null;
        }

        return connection;
    }

    public static ArrayList<Person> findAllPeople() {
        Connection connection = null;
        Statement statement = null;
        ArrayList<Person> people = new ArrayList<>();

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM PLACE;");

            while (set.next()) {
                people.add(new Person(set.getString("firstname"), set.getString("lastname"), set.getInt("age"),
                        set.getInt("creditcard"), set.getInt("ssn")));
            }
            set.close();
            statement.close();
            connection.close();

            System.out.println("(Find All People) done successfully");
        } catch (Exception e) {
            System.out.println(e);
            people = null;
        }
        return people;

    }

    public static Person selectPerson(int id) {
        Connection connection = null;
        Statement statement = null;
        Person person = new Person();
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM PLACE where ID=" + id + ";");
            while (set.next()) {
                person.setFirstName(set.getString("firstname"));
                person.setLastName(set.getString("lastname"));
                person.setAge(set.getInt("age"));
                person.setCreditCard(set.getInt("creditcard"));
                person.setSsn(set.getInt("ssn"));
            }
            set.close();
            statement.close();
            connection.close();

            System.out.println("(Select Person " + id + ") is successfully completed");
        } catch (Exception e) {
            System.out.println(e);
            person = null;
        }
        return person;

    }

    public static Connection deletePerson(int id) {
        Connection connection = null;
        Statement field = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            field = connection.createStatement();
            String sql = "DELETE from PLACE where ID=" + id + ";";
            field.executeUpdate(sql);
            connection.commit();
            connection.close();

            System.out.println("(Deleted Person " + id + ") is successfully deleted");
        } catch (Exception e) {
            System.out.println(e);
            connection = null;
        }
        return connection;

    }
}
