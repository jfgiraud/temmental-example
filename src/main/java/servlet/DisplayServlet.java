package servlet;

import bean.AsModel;
import bean.Location;
import bean.Property;
import persistence.LocationDao;
import service.PropertyService;
import temmental.Template;
import temmental.TemplateUtils;
import temmental.Transform;
import utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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
        String propertiesPath = getServletContext().getRealPath("annonces.properties");
        Map<String, Object> transforms = new HashMap<String, Object>();

        final LocationDao locationDao = new LocationDao();
        transforms.put("s2loc", new Transform<String, Location>() {
            public Location apply(String s) {
                try {
                    return locationDao.find(s).get(0);
                } catch (SQLException e) {
                    return null;
                }
            }
        });

        try {
            transforms.put("asint", new Transform<String,Number>() {
                public Number apply(String s) {
                    return Integer.parseInt(s);
                }
            });
            transforms.put("is_today", new Transform<String,Boolean>() {
                public Boolean apply(String s) {
                    try {
                        return DateUtils.isToday(s, "yyyy-mm-dd");
                    } catch (ParseException e) {
                        return false;
                    }
                }
            });
            transforms.put("toModel", AsModel.class.getDeclaredMethod("toModel"));
            transforms.put("contains", Collection.class.getDeclaredMethod("contains", Object.class));
            tpl = new Template(path, transforms, TemplateUtils.readProperties(propertiesPath), Locale.FRANCE);
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

        model.put("selected_cities", Arrays.asList("LUDON-MEDOC-33290"));
        model.put("cities", propertyService.getLocations());
        model.put("properties", TemplateUtils.transform(propertyService.getAll(),
                new Transform<Property, Map>() {
                    public Map apply(Property property) {
                        return property.toModel();
                    }
                }));

        tpl.printFile(out, model);
        out.flush();
    }


}
