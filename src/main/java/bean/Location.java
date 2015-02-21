package bean;

import java.util.HashMap;
import java.util.Map;

public class Location implements AsModel {

    private String name;
    private String zipCode;
    private String normalizedName;

    public Location(String normalizedName, String name, String zipCode) {
        this.normalizedName = normalizedName;
        this.name = name;
        this.zipCode = zipCode;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public String getName() {
        return name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Map<String,String> toModel() {
        Map<String, String> h = new HashMap<String, String>();
        h.put("city", name);
        h.put("CITY", normalizedName);
        h.put("zip", zipCode);
        return h;
    }
}
