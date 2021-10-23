package model.service;


import model.entity.Tasks;
import model.repository.TasksDataAccess;

import java.util.List;

public class TaskService implements TaskServiceBase {
    private static final TaskService taskService = new TaskService();

    private TaskService() {
    }

    public static TaskService getInstance() {
        return taskService;
    }

    @Override
    public void register(Tasks task) throws Exception {
        try (TasksDataAccess tasksDataAccess = new TasksDataAccess()) {
            tasksDataAccess.insert(task);
        }
    }

    @Override
    public void removeTask(long id) throws Exception {
        try (TasksDataAccess tasksDataAccess = new TasksDataAccess()) {
            tasksDataAccess.delete(id);
        }
    }

    @Override
    public List<Tasks> findAll() throws Exception {
        try (TasksDataAccess tasksDataAccess = new TasksDataAccess()) {
            return tasksDataAccess.selectAll();

        }
    }

    @Override
    public Tasks findTaskById(long id) throws Exception {
        try (TasksDataAccess tasksDataAccess = new TasksDataAccess()) {
            return tasksDataAccess.selectOneTaskById(id);
        }
    }

    @Override
    public Tasks findTaskByTaskId(long taskId) throws Exception {
        try (TasksDataAccess tasksDataAccess = new TasksDataAccess()) {
            return tasksDataAccess.selectOneTaskByTaskId(taskId);
        }
    }

    @Override
    public Tasks findTasksByTitle(String title) throws Exception {
        try (TasksDataAccess tasksDataAccess = new TasksDataAccess()) {
            return tasksDataAccess.selectOneTaskByTitle(title);
        }
    }

    @Override
    public Tasks findTaskByDescription(String description) throws Exception {
        try (TasksDataAccess tasksDataAccess = new TasksDataAccess()) {
            return tasksDataAccess.selectOneTaskByDescription(description);
        }
    }


    @Override
    public void update(Tasks task) throws Exception {
        try (TasksDataAccess tasksDataAccess = new TasksDataAccess()) {
            tasksDataAccess.update(task);
        }
    }


}
