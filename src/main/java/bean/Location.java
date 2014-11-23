package bean;

public class Location {

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


}
