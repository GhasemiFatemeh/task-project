package model.repository;


import common.exception.InvalidUserNameOrPassword;
import model.entity.Person;
import model.entity.Roles;
import model.repository.common.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static model.repository.common.JDBC.ORACLE_XE;

public class RolesDataAccess implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public RolesDataAccess() throws Exception{
        connection= JDBC.getConnection(ORACLE_XE);
        connection.setAutoCommit(false);
    }

    public void insert(Roles roles) throws Exception{
        preparedStatement= connection.prepareStatement("insert into roles(userName, roleName) values (?,?)");
        preparedStatement.setString(1,roles.getUserName());
        preparedStatement.setString(2,roles.getRoleName());
        preparedStatement.executeUpdate();
    }

    public void delete(Roles roles) throws Exception{
        preparedStatement= connection.prepareStatement("delete from roles where userName=?");
        preparedStatement.setString(1, roles.getUserName());
        preparedStatement.executeUpdate();
    }

    public void update(Roles roles) throws Exception{
        preparedStatement= connection.prepareStatement("update roles set roleName = ? where userName=?;");
        preparedStatement.setString(1, roles.getUserName());
        preparedStatement.setString(2, roles.getRoleName());
        preparedStatement.executeUpdate();
    }

    public List<Roles> selectAll() throws Exception{
        preparedStatement= connection.prepareStatement("select * from roles");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Roles> rolesList= new ArrayList<>();
        while (resultSet.next()){
            Roles roles= new Roles(resultSet.getString("userName"), resultSet.getString("roleName"));
            rolesList.add(roles);
        }
        return rolesList;
    }

    public List<Roles> selectByUserNameAndPassword(Person person) throws Exception{
        preparedStatement= connection.prepareStatement("select * from person where upper(userName)= upper (?) and password=?");
        preparedStatement.setString(1, person.getUserName());
        preparedStatement.setString(2, person.getPassword());
        ResultSet resultSet= preparedStatement.executeQuery();
        if (resultSet.next()){
            preparedStatement=connection.prepareStatement("select * from roles where username=?");
            preparedStatement.setString(1, person.getUserName());
            resultSet= preparedStatement.executeQuery();
            List<Roles> rolesList =new ArrayList<>();
            while (resultSet.next()) {
                Roles roles = new Roles(resultSet.getString("roleName"));
                rolesList.add(roles);
            }
            return rolesList;
        }
        throw new InvalidUserNameOrPassword();
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
