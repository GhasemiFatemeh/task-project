package controller;

import common.exception.ExceptionWrapper;
import model.entity.Tasks;
import model.service.TaskService;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


@WebServlet("/update")
public class Update extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Tasks tasks= new Tasks()
                    .setId(Long.parseLong(req.getParameter("id")))
                    .setTaskId(Long.parseLong(req.getParameter("taskId")))
                    .setTitle(req.getParameter("title"))
                    .setDescription(req.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
            TaskService.getInstance().update(tasks);
        }catch (Exception e){
            ExceptionWrapper.getMessage(e);
        }
    }
}
