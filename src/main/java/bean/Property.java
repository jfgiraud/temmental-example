package bean;

import java.util.HashMap;
import java.util.Map;

public class Property implements AsModel {

    private String description;
    private String title;
    private String dateTime;
    private String imageUrl;
    private String url;
    private String area;
    private String price;
    private String city;
    private String agency;
    private String site;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> toModel() {
        Map<String,Object> model = new HashMap<String, Object>();
        model.put("description", description);
        model.put("title", title);
        model.put("date_time", dateTime);
        model.put("url", url);
        model.put("image_url", imageUrl);
        model.put("area", area);
        model.put("price", price);
        model.put("city", city);
        model.put("agency", agency);
        model.put("site", site);
        return model;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }
}
