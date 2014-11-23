package persistence;


import bean.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDao {

    public List<Location> find(String normalizedName) throws SQLException {
        Connection sqlConnection = SqlConnectionManager.getConnection(PropertyDao.TEST_DB_URL);
        try {
            PreparedStatement st = sqlConnection.prepareStatement("select nom_normalise, ville, code_postal from communes where nom_normalise=?");
            st.setString(1, normalizedName);
            try {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    result.add(rs.getString("commune"));
                }
            } finally {
                st.close();
            }
            return result;
        } finally {
            SqlConnectionManager.releaseConnection(sqlConnection);
        }
    }

}
