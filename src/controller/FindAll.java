package controller;

import common.exception.ExceptionWrapper;
import common.jsonprovider.JsonProvider;
import model.entity.Tasks;
import model.service.TaskService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/findAll")
public class FindAll extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/plain");
            resp.getWriter().println(JsonProvider.toJson(TaskService.getInstance().findAll()));
        } catch (Exception e) {
            ExceptionWrapper.getMessage(e);
        }
    }

}
