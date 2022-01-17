package vehicle;

import static util.InputFromCMD.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class Car extends Vehicle{
    private String type;
    private int yearOfManufacture;

    public Car() {
        super.setTypeOfVehicle("Car");
        inputInfo();
        setKeywordsList(Arrays.asList(getName().split(" ")).stream().map(x -> x.trim()).filter(x -> !x.equals("")).collect(Collectors.toList()));
    }

    public Car(String id, String name, String color, double price, String brand, String type, int yearOfManufact) {
        super(id, name, color, price, brand);
        this.type = type;
        this.yearOfManufacture = yearOfManufact;
        super.setTypeOfVehicle("Car");
    }

    @Override
    public void inputInfo() {
        super.inputInfo();
        String type = inputString("Type: ", "", "Please try again: ");
        if (!type.equals("")) this.type = type;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer year = inputInteger("Year of manufature: ", "", "Please try again: ", 1885, currentYear);
        if (!year.equals(-1)) yearOfManufacture = year;
    }

    @Override
    public void outputInfo() {
        super.outputInfo();
        System.out.printf("%15s|%10d|\n", type, yearOfManufacture);
    }

    @Override
    public void updateInfo(boolean isAdmin) {
        super.updateInfo(isAdmin);
        String type = inputString("Type: ", "", "Please try again: ");
        if (!type.equals("")) this.type = type;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int yearOfManufacture = inputInteger("Year of manufature: ", "", "Please try again: ", 1885, currentYear);
        if(yearOfManufacture >= 0) this.yearOfManufacture = yearOfManufacture;
    }

    @Override
    public String toString() {
        return super.toString()
                + "    Type: " + type
                + "    Year of manufacture: " + yearOfManufacture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }
}
