package servlet;

import bean.Property;
import service.PropertyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DisplayServlet extends HttpServlet {

    private PropertyService propertyService = new PropertyService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("hello man!");
        for (Property property : propertyService.getAll()) {
            out.println("Hello " + property.getDescription());
        }
        out.flush();
        out.close();
    }

}
