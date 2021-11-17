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
    @Produces("application/json")
    @Path("/register")
    public Response register(@HeaderParam("body") String body ,@QueryParam("taskId") String taskId, @QueryParam("title") String title, @QueryParam("description") String description) {
        try {
            System.out.println("body"+body);
            System.out.println(taskId);
            System.out.println(title);
            System.out.println("aa"+ description);
            Tasks task = new Tasks();
            task.setTaskId(Long.parseLong(taskId)).setTitle(title).setDescription(description);
            //validation
            task.setTitle(Validation.getInstance().protectFromHtmlInjection(task.getTitle()));
            task.setDescription(Validation.getInstance().protectFromHtmlInjection(Tools.getInstance().convertToNormalBreakLine(task.getDescription())));
            //
            TaskService.getInstance().register(task);
            return Response
                    .status(Response.Status.OK)
                    .entity(task)
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
                    .setDescription(Tools.getInstance().convertToNormalBreakLine(description));
            //validation
            task.setTitle(Validation.getInstance().protectFromHtmlInjection(task.getTitle()));
            task.setDescription(Validation.getInstance().protectFromHtmlInjection(task.getDescription()));
            //
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

