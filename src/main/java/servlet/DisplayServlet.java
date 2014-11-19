package servlet;

import bean.Property;
import service.PropertyService;
import temmental2.Template;
import temmental2.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

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


        String path = getServletContext().getRealPath("annonces.tpl");
        Map<String, ? extends Object> transforms = new HashMap<String, Object>();
        try {
            Template tpl = new Template(path, transforms, new Properties(), Locale.FRANCE);
            tpl.printFile(out);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
        out.close();
    }

}
