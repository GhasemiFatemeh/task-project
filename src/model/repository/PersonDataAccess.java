package model.repository;

import model.entity.Person;
import model.repository.common.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static model.repository.common.JDBC.ORACLE_XE;

public class PersonDataAccess implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;
    public PersonDataAccess() throws Exception{
        connection= JDBC.getConnection(ORACLE_XE);
        connection.setAutoCommit(false);
    }

    public void insert(Person person) throws Exception{
        preparedStatement=connection.prepareStatement("select people_seq.nextval id from dual");
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        person.setId(resultSet.getLong("id"));
        preparedStatement= connection.prepareStatement("insert into person(id, name, family) values (?,?,?)");
        preparedStatement.setLong(1, person.getId());
        preparedStatement.setString(2, person.getUserName());
        preparedStatement.setString(3, person.getPassword());
        preparedStatement.executeUpdate();
    }

    public void delete(long id) throws Exception{
        preparedStatement= connection.prepareStatement("delete from person where id=?");
        preparedStatement.setLong(1,id);
        preparedStatement.executeUpdate();
    }

    public void update(Person person) throws Exception{
        preparedStatement=connection.prepareStatement("update person set userName=?, password=? where id=?");
        preparedStatement.setString(1, person.getUserName());
        preparedStatement.setString(2, person.getPassword());
        preparedStatement.setLong(3, person.getId());
        preparedStatement.executeUpdate();
    }

    public List<Person> selectAll() throws Exception{
        preparedStatement= connection.prepareStatement("select * from person");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> personList= new ArrayList<>();
        while (resultSet.next()){
            Person person =new Person(resultSet.getLong("id"), resultSet.getString("userName"), resultSet.getString("password"));
            personList.add(person);
        }
        return personList;
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
