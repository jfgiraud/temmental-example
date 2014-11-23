package service;

import bean.Location;
import bean.Property;
import persistence.LocationDao;
import persistence.PropertyDao;

import java.sql.SQLException;
import java.util.List;

public class LocationService {

    LocationDao dao = new LocationDao();

    public List<Location> find(String normalizedName) {
        try {
            return dao.find(normalizedName);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

}
