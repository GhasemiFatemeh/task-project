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

@WebServlet("/delete")
public class Remove extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TaskService.getInstance().removeTask(Long.parseLong(req.getParameter("id")));
        }catch (Exception e){
            ExceptionWrapper.getMessage(e);
        }
    }

}
