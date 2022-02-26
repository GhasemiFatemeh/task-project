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
import java.util.List;


@WebServlet("/findAll")
public class FindAll extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
            List<Tasks> tasksList = TaskService.getInstance().findAll();
            req.setAttribute("tasksList", tasksList);
        } catch (Exception e) {
            ExceptionWrapper.getMessage(e);
        }
    }
}
