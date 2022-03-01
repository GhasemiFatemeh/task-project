package model.entity;

import javax.persistence.*;

@Table(name = "tasks")
@Entity(name = "tasks")
public class Tasks {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "taskId")
    private long taskId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    public Tasks() {
    }

    public Tasks(long id, long taskId, String title, String description) {
        this.id = id;
        this.taskId = taskId;
        this.title = title;
        this.description = description;
    }

    public Tasks(long taskId, String title, String description) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
    }

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
