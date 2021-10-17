package model.entity;

public class Tasks {
    private long id;
    private long taskId;
    private String title;
    private String description;

    public long getId() {
        return id;
    }

    public Tasks setId(long id) {
        this.id = id;
        return this;
    }

    public long getTaskId() {
        return taskId;
    }

    public Tasks setTaskId(long taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Tasks setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Tasks setDescription(String description) {
        this.description = description;
        return this;
    }


}
