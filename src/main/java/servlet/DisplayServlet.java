package servlet;

import service.PropertyService;
import temmental.Template;
import temmental.TemplateUtils;
import temmental.Transform;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
        Map<String, Object> transforms = new HashMap<String, Object>();

        final Transform<String, Map<String, String>> stringToMap = new Transform<String, Map<String, String>>() {
            public Map<String, String> apply(String s) {
                Map<String, String> q = new HashMap<String, String>();
                q.put("label", s);
                return q;
            }
        };

        transforms.put("s2m", new Transform<Object,List<Map<String,String>>>() {
            public List<Map<String, String>> apply(Object strings) {
                return TemplateUtils.transform((List<String>) strings, stringToMap);
            }
        });

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

        Map<String, Object> model = new HashMap<String, Object>();

        final List<String> r = propertyService.getLocations();

        model.put("communes", r);

        tpl.printFile(out, model);
        out.flush();
    }


}
