package vehicle;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.stream.Collectors;

import static util.InputFromCMD.*;

public class Motorbike extends Vehicle implements Sound{
    private double speed;
    private boolean requireLicense;

    public Motorbike() {
        super.setTypeOfVehicle("Motorbike");
        inputInfo();
        setKeywordsList(Arrays.asList(getName().split(" ")).stream().map(x -> x.trim()).filter(x -> !x.equals("")).collect(Collectors.toList()));
    }

    public Motorbike(String id, String name, String color, double price, String brand, double speed, boolean requireLicense) {
        super(id, name, color, price, brand);
        super.setTypeOfVehicle("Motorbike");
        this.speed = speed;
        this.requireLicense = requireLicense;

    }

    @Override
    public void inputInfo() {
        super.inputInfo();
        Double speed = inputDouble("Speed: ", "", "Please try again: ");
        if (!speed.equals(-1.0)) this.speed = speed;
        String tmp = inputString("Is require license (Y/N): ", "", "Please try again: ");
        while (true) {
            if (tmp.equalsIgnoreCase("Y")) {
                requireLicense = true;
                break;
            } else if (tmp.equalsIgnoreCase("N")) {
                requireLicense = false;
                break;
            } else {
                tmp = inputString("Please try again: ", "", "Please try again: ");
            }
        }


    }

    @Override
    public void outputInfo() {
        super.outputInfo();
        System.out.printf("%15.2f|%10b|\n", speed, requireLicense);
    }

    @Override
    public void updateInfo(boolean isAdmin) {
        super.updateInfo(isAdmin);
        this.speed = inputDouble("Speed: ", "", "Please try again: ");
        String tmp = inputString("Is require license (Y/N): ", "", "Please try again: ");
        while (true) {
            if (tmp.equalsIgnoreCase("Y")) {
                requireLicense = true;
                break;
            } else if (tmp.equalsIgnoreCase("N")) {
                requireLicense = false;
                break;
            } else {
                tmp = inputString("Please try again: ", "", "Please try again: ");
            }
        }
    }
    @Override
    public String toString() {
        return super.toString()
                + "    Speed: " + speed
                + "    Required license: " + requireLicense;
    }

    @Override
    public void makeSound() {
        System.out.println("Tin tin tin");
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isRequireLicense() {
        return requireLicense;
    }

    public void setRequireLicense(boolean requireLicense) {
        this.requireLicense = requireLicense;
    }
}
