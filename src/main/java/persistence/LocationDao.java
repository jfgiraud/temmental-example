package persistence;


import bean.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDao {

    public Location first(String normalizedNameZipCode) throws SQLException {
        return find(normalizedNameZipCode).get(0);
    }

    public List<Location> find(String normalizedNameZipCode) throws SQLException {
        String normalizedName = normalizedNameZipCode.substring(0, normalizedNameZipCode.lastIndexOf("-"));
        String zipCode = normalizedNameZipCode.substring(normalizedNameZipCode.lastIndexOf("-")+1);
        Connection sqlConnection = SqlConnectionManager.getConnection(PropertyDao.TEST_DB_URL);
        try {
            PreparedStatement st = sqlConnection.prepareStatement("select nom_normalise, ville, code_postal from communes where nom_normalise=? and code_postal=?");
            st.setString(1, normalizedName);
            st.setString(2, zipCode);
            List<Location> result = new ArrayList<Location>();
            try {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    result.add(asLocation(rs.getString("nom_normalise"), rs.getString("ville"), rs.getString("code_postal")));
                }
            } finally {
                st.close();
            }
            return result;
        } finally {
            SqlConnectionManager.releaseConnection(sqlConnection);
        }
    }

    private Location asLocation(String normalizedName, String name, String zipCode) {
        return new Location(normalizedName, name, zipCode);
    }

}
