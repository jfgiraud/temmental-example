package persistence;

import bean.Property;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyDao {

    public static final String TEST_DB_URL = "jdbc:sqlite:file:src/main/resources/annonces.db";

    public List<Property> getAll() throws SQLException {
        List<Property> result = new ArrayList<Property>();
        Connection sqlConnection = SqlConnectionManager.getConnection(TEST_DB_URL);
        try {
            PreparedStatement st = sqlConnection.prepareStatement("select aid, dateheure, classification, image_url, url, titre, description, commune, prix, surface, agence, site, creation from annonces");
            try {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Property property = new Property();
                    property.setDescription(rs.getString("description"));
                    property.setTitle(rs.getString("titre"));
                    property.setDateTime(rs.getString("dateheure"));
                    property.setImageUrl(rs.getString("image_url"));
                    property.setUrl(rs.getString("url"));
                    property.setArea(rs.getString("surface"));
                    property.setPrice(rs.getString("prix"));
                    property.setCity(rs.getString("commune"));
                    property.setAgency(rs.getString("agence"));
                    property.setSite(rs.getString("site"));
                    result.add(property);
                }
            } finally {
                st.close();
            }
            return result;
        } finally {
            SqlConnectionManager.releaseConnection(sqlConnection);
        }
    }

    public List<String> getLocations() throws SQLException {
        List<String> result = new ArrayList<String>();
        Connection sqlConnection = SqlConnectionManager.getConnection(TEST_DB_URL);
        try {
            PreparedStatement st = sqlConnection.prepareStatement("select distinct commune from annonces order by commune asc");
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
