package servlet;

import bean.Property;
import service.PropertyService;
import temmental.Template;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

    private Template tpl;

    @Override
    public void init() throws ServletException {
        super.init();
        initTemplate();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        initTemplate();
        super.service(req, res);
    }

    private void initTemplate() throws ServletException {
        String path = getServletContext().getRealPath("annonces.tpl");
        Map<String, ? extends Object> transforms = new HashMap<String, Object>();
        try {
            tpl = new Template(path, transforms, new Properties(), Locale.FRANCE);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.addHeader("Pragma", "no-cache");

        PrintWriter out = response.getWriter();
        tpl.printFile(out);
        out.flush();
        //out.close();
    }

}
