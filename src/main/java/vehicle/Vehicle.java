package vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static util.InputFromCMD.*;

public abstract class Vehicle implements Serializable {
    private String id;
    private String name;
    private String color;
    private double price;
    private String brand;
    private String typeOfVehicle;
    private List<String> keywordsList;

    public Vehicle() {

    }

    public Vehicle(String id, String name, String color, double price, String brand) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        keywordsList = Arrays.asList(name.split(" ")).stream().map(x -> x.trim()).filter(x -> !x.equals("")).map(x -> x.toUpperCase()).collect(Collectors.toList());
        keywordsList.add(id.toUpperCase());

    }


    public void inputInfo() {
        System.out.println("Input " + typeOfVehicle + " information");
        id = inputString("ID: ", "", "Please try again: ");
        name = inputString("Name: ", "", "Please try again: ");
        color = inputString("Color: ", "", "Please try again: ");
        price = inputDouble("Price: ", "", "Please try again: ");
        brand = inputString("Brand: ", "", "Please try again: ");
    }

    public void outputInfo() {
        System.out.printf("|%-10s|%10s|%20s|%10s|%10.2f|%15s|", typeOfVehicle, id, name, color, price, brand);
    }

    public void updateInfo(boolean isAdmin) {
        if (isAdmin) {
            String id = inputString("ID: ", "", "Please try again: ");
            if (!id.equals("")) this.id = id;
        }
        String name = inputString("Name: ", "", "Please try again: ");
        if (!name.equals("")) this.name = name;
        String color = inputString("Color: ", "", "Please try again: ");
        if (!color.equals("")) this.color = color;
        Double price = inputDouble("Price: ", "", "Please try again: ");
        if (price >= 0) this.price = price;
        String brand = inputString("Brand: ", "", "Please try again: ");
        if (!brand.equals("")) this.brand = brand;
        keywordsList = Arrays.asList(name.split(" ")).stream().map(x -> x.trim()).filter(x -> !x.equals("")).collect(Collectors.toList());
        keywordsList.add(id);
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "    Name: " + name
                + "    Color: " + color
                + "    Price: " + price
                + "    Brand: " + brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public List<String> getKeywordsList() {
        return keywordsList;
    }

    public void setKeywordsList(List<String> keywordsList) {
        this.keywordsList = keywordsList;
        keywordsList.add(id);
    }
}
