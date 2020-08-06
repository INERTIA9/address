package com.bridgelabs.dao.impl;

import com.bridgelabs.dao.IAddressBookDAO;
import com.bridgelabs.model.Person;
import com.bridgelabs.utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDAOImplDB implements IAddressBookDAO {

    public static final String INSERT_PERSON_QUERY = "insert into t_person ( first_name, last_name, city_name, state_name, zip_code, phone_number ) values (?,?,?,?,?,?)";
    private static final String SELECT_PERSON_QUERY = "select * from t_person";
    private static final String QUERY = "select count(*) AS total from t_person where first_name = ? and last_name = ?";
    private static final String UPDATE_PERSON = "Update t_person set city_name = ? , state_name = ? , zip_code =? , phone_number = ? where first_name = ? and last_name = ?";
    private static final String DELETE_PERSON = "delete from t_person where first_name = ? and last_name = ?";
    private static final String FIND_PERSON_BY_CITY_AND_STATE = "select * from t_person where city_name = ? and state_name = ?";
    private static final String FIND_PERSON_BY_CITY = "select * from t_person where city_name = ?";
    private static final String FIND_PERSON_BY_STATE = "select * from t_person where state_name = ?";

    @Override
    public void addPerson(Person person) {
        try (java.sql.Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_PERSON_QUERY)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getCity());
            stmt.setString(4, person.getState());
            stmt.setInt(5, person.getZip());
            stmt.setLong(6, person.getPhoneNumber());
            stmt.executeUpdate();
            System.out.println("Person Data inserted successfully");
        } catch (SQLException e) {
            System.out.println("Person Already Exist");
        }
    }

    @Override
    public List<Person> findByCityAndState(Person person) {
        List<Person> personList = new ArrayList<>();
        try (java.sql.Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(FIND_PERSON_BY_CITY_AND_STATE)) {
            stmt.setString(1, person.getCity());
            stmt.setString(2, person.getState());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Person person1 = new Person();
                person1.setFirstName(rs.getString("first_name"));
                person1.setLastName(rs.getString("last_name"));
                person1.setCity(rs.getString("city_name"));
                person1.setState(rs.getString("state_name"));
                person1.setZip(Integer.parseInt(rs.getString("zip_code")));
                person1.setPhoneNumber(Long.valueOf(rs.getString("phone_number")));
                personList.add(person1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  personList;
    }

    @Override
    public List<Person> findByCity(String city) {
        List<Person> personList = new ArrayList<>();
        try (java.sql.Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(FIND_PERSON_BY_CITY)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Person person1 = new Person();
                person1.setFirstName(rs.getString("first_name"));
                person1.setLastName(rs.getString("last_name"));
                person1.setCity(rs.getString("city_name"));
                person1.setState(rs.getString("state_name"));
                person1.setZip(Integer.parseInt(rs.getString("zip_code")));
                person1.setPhoneNumber(Long.valueOf(rs.getString("phone_number")));
                personList.add(person1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  personList;
    }

    @Override
    public List<Person> findByState(String state) {
        List<Person> personList = new ArrayList<>();
        try (java.sql.Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(FIND_PERSON_BY_STATE)) {
            stmt.setString(1, state);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Person person1 = new Person();
                person1.setFirstName(rs.getString("first_name"));
                person1.setLastName(rs.getString("last_name"));
                person1.setCity(rs.getString("city_name"));
                person1.setState(rs.getString("state_name"));
                person1.setZip(Integer.parseInt(rs.getString("zip_code")));
                person1.setPhoneNumber(Long.valueOf(rs.getString("phone_number")));
                personList.add(person1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  personList;
    }

    @Override
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        try (java.sql.Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_PERSON_QUERY)) {
            while (rs.next()) {
                Person person = new Person();
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setCity(rs.getString("city_name"));
                person.setState(rs.getString("state_name"));
                person.setZip(Integer.parseInt(rs.getString("zip_code")));
                person.setPhoneNumber(Long.valueOf(rs.getString("phone_number")));
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  personList;
    }

    @Override
    public int findByFirstNameAndLastName(Person person) {
        int count = 0;
        try (java.sql.Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(QUERY)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void editPerson(Person person) {
        try (java.sql.Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_PERSON)) {
            stmt.setString(1, person.getCity());
            stmt.setString(2, person.getState());
            stmt.setInt(3, person.getZip());
            stmt.setLong(4, person.getPhoneNumber());
            stmt.setString(5, person.getFirstName());
            stmt.setString(6, person.getLastName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePerson(Person person) {
        try (java.sql.Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_PERSON)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
