package controller;

import common.jsonprovider.JsonProvider;
import model.entity.Tasks;
import model.service.TaskService;
import org.codehaus.jackson.map.ObjectMapper;

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
            resp.getWriter().println(JsonProvider.toJson(TaskService.getInstance().findTasks(req.getParameter("input"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
