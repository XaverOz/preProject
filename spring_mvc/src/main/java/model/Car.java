package model;

public class Car {

    private String model;

    private String manufacturer;

    private String carClass;

    public Car(String model, String manufacturer, String carClass) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.carClass = carClass;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
