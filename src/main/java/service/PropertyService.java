package service;

import bean.Property;
import persistence.PropertyDao;

import java.sql.SQLException;
import java.util.List;

public class PropertyService {

    PropertyDao dao = new PropertyDao();

    public List<Property> getAll() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

}
