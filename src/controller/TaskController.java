package controller;

import common.exception.ExceptionWrapper;
import model.entity.Tasks;
import model.service.TaskService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/manageTasks")
public class TaskController {

    @GET
    @Produces("application/json")
    @Path("/register")
    public Response register(@QueryParam("taskId") String taskId, @QueryParam("title") String title, @QueryParam("description") String description) {
        try {
            Tasks task = new Tasks();
            task.setTaskId(Long.parseLong(taskId)).setTitle(title).setDescription(description);
            TaskService.getInstance().register(task);
            return Response
                    .status(Response.Status.OK)
                    .entity(task)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @GET
    @Path("/findAll")
    @Produces("application/json")
    public Object findAll() {
        try {
            return TaskService.getInstance().findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return ExceptionWrapper.getMessage(e);
        }
    }

    @GET
    @Path("/delete")
    @Produces("application/json")
    public Response remove(@QueryParam("id") long id) {
        try {
            TaskService.getInstance().removeTask(id);
            return Response
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @GET
    @Path("/update")
    @Produces("application/json")
    public Response update(@QueryParam("id") String id, @QueryParam("taskId") String taskId, @QueryParam("title") String title, @QueryParam("description") String description) {
        try {
            Tasks tasks = new Tasks();
            tasks.setId(Long.parseLong(id))
                    .setTaskId(Long.parseLong(taskId))
                    .setTitle(title)
                    .setDescription(description);
            TaskService.getInstance().update(tasks);
            return Response
                    .status(Response.Status.OK)
                    .entity(tasks)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

}

