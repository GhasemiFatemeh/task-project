package controller;

import model.entity.Tasks;
import model.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findTasks")
public class FindTasks extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Tasks> foundTasks = TaskService.getInstance().findTasks(req.getParameter("input"));
            req.setAttribute("foundTasks", foundTasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
