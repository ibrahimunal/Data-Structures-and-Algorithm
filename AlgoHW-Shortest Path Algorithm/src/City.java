public class City {

    private int id;
    private int xCoord;
    private int yCoord;
    private String CityName;


    public City(int id, int xCoord, int yCoord, String cityName) {
        this.id = id;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        CityName = cityName;
    }

    public int getId() {
        return id;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public String getCityName() {
        return CityName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
}
