package controller;

import com.coverity.security.Escape;
import common.Tools;
import common.exception.ExceptionWrapper;
import model.entity.Tasks;
import model.service.TaskService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/manageTasks")
public class TaskController {

    @POST
    @Consumes("text/plain")
    @Path("/register")
    public Response register( String desc,@QueryParam("taskId") String taskId, @QueryParam("title") String title) {
        try {
            Tasks task = new Tasks();
            task.setTaskId(Long.parseLong(taskId))
                    .setTitle(title)
                    .setDescription(desc);
            TaskService.getInstance().register(task);
            return Response
                    .status(Response.Status.OK)
                    .entity(task)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .type(MediaType.TEXT_PLAIN)
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
            Tasks task = new Tasks();
            task.setId(Long.parseLong(id))
                    .setTaskId(Long.parseLong(taskId))
                    .setTitle(title)
                    .setDescription(description);
            TaskService.getInstance().update(task);
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
    @Path("/findByTaskId")
    public Response findByTaskId(@QueryParam("taskId") String taskId){
        try{
            TaskService.getInstance().findTaskByTaskId(Long.parseLong(taskId));
            return Response
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        }
        catch (Exception e){
           return Response
                    .status(Response.Status.FORBIDDEN)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }
}

