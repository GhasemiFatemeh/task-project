package model.service;

import model.entity.Tasks;

import java.util.List;

public interface TaskServiceBase {

    void register(Tasks task) throws Exception;

    void removeTask(long id) throws Exception;

    List<Tasks> findAll() throws Exception;

    Tasks findTaskById(long id) throws Exception;

    Tasks findTaskByTaskId(long taskId) throws Exception;

    Tasks findTasksByTitle(String title) throws Exception;

    Tasks findTaskByDescription(String description) throws Exception;

    List<Tasks> findTasks(String input) throws Exception;

    void update(Tasks task) throws Exception;

}
