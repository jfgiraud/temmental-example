package service;

import bean.Location;
import persistence.LocationDao;

import java.sql.SQLException;
import java.util.List;

public class LocationService {

    LocationDao dao = new LocationDao();

    public List<Location> find(String normalizedNameZipCode) {
        try {
            return dao.find(normalizedNameZipCode);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

}
