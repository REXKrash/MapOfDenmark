package bfst21.address;

import java.util.List;


public class CityController {

    private static CityController instance;
    private List<City> cityList;

    public void readCities() {
        FileReader filereader = new FileReader();
        this.cityList = filereader.getCityList();
    }

    public String getCityNameFromPostcode(String postcode) {
        for (City c : cityList) {
            if (c.getPostcode() == Integer.parseInt(postcode)) {
                return c.getName();
            }
        }
        return "";
    }

    public static CityController getInstance() {
        if (instance == null) {
            instance = new CityController();
        }
        return instance;
    }
}