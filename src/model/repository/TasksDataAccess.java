package model.repository;

import exception.TaskNotFound;
import model.entity.Tasks;
import model.repository.common.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TasksDataAccess implements AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TasksDataAccess() throws Exception {
        connection = JDBC.getConnection();
        connection.setAutoCommit(false);
    }

    public void insert(Tasks tasks) throws Exception {
        preparedStatement = connection.prepareStatement("select task_seq.nextVal id from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        long id = resultSet.getLong("id");
        tasks.setId(id);
        preparedStatement = connection.prepareStatement("insert into tasks(id,taskId, title, description) values (?,?,?,?)");
        preparedStatement.setLong(1, tasks.getId());
        preparedStatement.setLong(2, tasks.getTaskId());
        preparedStatement.setString(3, tasks.getTitle());
        preparedStatement.setString(4, tasks.getDescription());
        preparedStatement.executeUpdate();
    }

    public void update(Tasks tasks) throws Exception {
        preparedStatement = connection.prepareStatement("update tasks set taskId=?, title =?, description=? where id=?");
        preparedStatement.setLong(1, tasks.getTaskId());
        preparedStatement.setString(2, tasks.getTitle());
        preparedStatement.setString(3, tasks.getDescription());
        preparedStatement.setLong(4, tasks.getId());
        preparedStatement.executeUpdate();
    }

    public void delete(long id) throws Exception {
        preparedStatement = connection.prepareStatement("delete tasks where id=?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    public List<Tasks> selectAll() throws Exception {
        preparedStatement = connection.prepareStatement("select * from tasks");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Tasks> tasksList = new ArrayList<>();
        while (resultSet.next()) {
            Tasks task = new Tasks()
                    .setId(resultSet.getLong("id"))
                    .setTaskId(resultSet.getLong("taskId"))
                    .setTitle(resultSet.getString("title"))
                    .setDescription(resultSet.getString("description"));
            tasksList.add(task);
        }
        return tasksList;
    }

    public Tasks selectOneTaskById(long id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from tasks where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Tasks task = new Tasks()
                    .setId(resultSet.getLong("id"))
                    .setTaskId(resultSet.getLong("taskId"))
                    .setTitle(resultSet.getString("title"))
                    .setDescription(resultSet.getString("description"));
            return task;
        } else {
            throw new TaskNotFound();
        }
    }

    public Tasks selectOneTaskByTitle(String title) throws Exception {
        preparedStatement = connection.prepareStatement("select * from tasks where title like ?");
        preparedStatement.setString(1, "%"+title+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Tasks task = new Tasks()
                    .setId(resultSet.getLong("id"))
                    .setTaskId(resultSet.getLong("taskId"))
                    .setTitle(resultSet.getString("title"))
                    .setDescription(resultSet.getString("description"));
            return task;
        } else {
            throw new TaskNotFound();
        }


    }

    public Tasks selectOneTaskByTaskId(long taskId) throws Exception {
        preparedStatement = connection.prepareStatement("select * from tasks where taskId=?");
        preparedStatement.setLong(1, taskId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Tasks task = new Tasks()
                    .setId(resultSet.getLong("id"))
                    .setTaskId(resultSet.getLong("taskId"))
                    .setTitle(resultSet.getString("title"))
                    .setDescription(resultSet.getString("description"));
            return task;
        } else {
            throw new TaskNotFound();
        }


    }

    public Tasks selectOneTaskByDescription(String description) throws Exception {
        preparedStatement = connection.prepareStatement("select * from tasks where discription like ?");
        preparedStatement.setString(1, "%"+description+ "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Tasks task = new Tasks()
                    .setId(resultSet.getLong("id"))
                    .setTaskId(resultSet.getLong("taskId"))
                    .setTitle(resultSet.getString("title"))
                    .setDescription(resultSet.getString("description"));
            return task;
        } else {
            throw new TaskNotFound();
        }

    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.commit();
        connection.close();
    }
}
