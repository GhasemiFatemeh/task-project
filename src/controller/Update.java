package controller;

import common.exception.ExceptionWrapper;
import model.entity.Tasks;
import model.service.TaskService;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;


@WebServlet("/update")
public class Update extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try{
            TaskService.getInstance().update(new Tasks(Long.parseLong(req.getParameter("taskId")), req.getParameter("title"), req.getParameter("description")));
        }catch (Exception e){
            ExceptionWrapper.getMessage(e);
        }
    }
}
