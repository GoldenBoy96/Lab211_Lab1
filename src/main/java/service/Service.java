package service;

import util.InputFromCMD;
import vehicle.Car;
import vehicle.Motorbike;
import vehicle.Vehicle;
import login.Login.*;

import java.util.*;
import java.util.stream.Collectors;

import static util.InputFromCMD.inputDouble;
import static util.InputFromCMD.inputString;

public class Service {
    Scanner SC = new Scanner(System.in);
    ReadWriteFile<Vehicle> RWF = new ReadWriteFile<>();

    List<Vehicle> list = new ArrayList<Vehicle>();

    public static void printMenu() {
        System.out.println("\nWelcome to Mr.QuanMX showroom!");
        System.out.println("1) Show the list of vehicles");
        System.out.println("2) Show the list of vehicles decending by price");
        System.out.println("3) Add a new vehicle");
        System.out.println("4) Search vehicle");
        System.out.println("5) Update a vehicle");
        System.out.println("6) Delete a vehicle");
        System.out.println("7) Open file");
        System.out.println("8) Save file");
        System.out.println("0) Exit");
    }

    public int choose() {
        int choose = InputFromCMD.inputInteger("What do you want to do: ", "", "Please try again: ", 0, 8);
        while (choose == -1) {
            choose = InputFromCMD.inputInteger("", "", "", 0, 8);
        }
        return choose;
    }

    public void showTheList() {
        if (list.size() == 0) {
            System.out.println("Nothing to show!");
        } else {
            System.out.printf("|%-10s|%10s|%20s|%10s|%10s|%15s|%15s|%10s|\n", "VEHICLE", "ID", "NAME", "COLOR", "PRICE", "BRAND", "TYPE/SPEED", "YOM/RL");
            list.stream().forEach(x -> x.outputInfo());
        }
    }

    public void showTheListDecendingByPrice() {
        if (list.size() == 0) {
            System.out.println("Nothing to show!");
        } else {
            System.out.printf("|%-10s|%10s|%20s|%10s|%10s|%15s|%15s|%10s|\n", "VEHICLE", "ID", "NAME", "COLOR", "PRICE", "BRAND", "TYPE/SPEED", "YOM/RL");
            list.stream().sorted(Comparator.comparingDouble(Vehicle::getPrice)).forEach(x -> x.outputInfo());

        }
    }

    public boolean isIDExist(String id) {
        for (Vehicle x : list) {
            if (id.equalsIgnoreCase(x.getId())) {
                return true;
            }
        }
        return false;
    }

    private String filePath = "";
    public boolean isSaved = true;
    public void openFile() {
        if (!isSaved) {
            System.out.println("Do you want to save before open new file?");
            System.out.println("1) Yes");
            System.out.println("2) No");
            int choose = InputFromCMD.inputInteger("Your choice: ", "", "", 1, 2);
            if(choose == 1) {
                saveAs();
            }
        }
        String filePath = InputFromCMD.inputString("Nhập file path: ", "", "");
        if (!filePath.equals("")) {
            List<Vehicle> tmpFile = RWF.readFile(filePath);
            if (!tmpFile.equals(null)) {
                this.filePath = filePath;
                list = tmpFile;
                System.out.println("File opened!");
                isSaved = true;
            }
        } else {
            System.out.println("File not found!");
        }
    }

    public void save() {
        if (filePath.equals("")) {
            saveAs();
        } else {
            RWF.writeFile(list, filePath);
        }

    }

    public void saveAs() {
        String filePath = InputFromCMD.inputString("Nhập file path: ", "", "");
        if (RWF.writeFile(list, filePath)) {
            this.filePath = filePath;
            isSaved = true;
        }

    }

    public void addAVehicle() {
        int choose = InputFromCMD.inputInteger("1) Car\n2) Motorbike\nKind of vehicle: ", "", "Please try again: ", 1, 2);
        switch (choose) {
            case 1:
                Car tmp1 = new Car();
                while (isIDExist(tmp1.getId())) {
                    String id = InputFromCMD.inputString("ID already exist, pLease reinput ID: ", "", "");
                    tmp1.setId(id);
                }
                list.add(tmp1);
                System.out.println("Car added successfully!");
                System.out.println(tmp1.toString());
                break;
            case 2:
                Motorbike tmp2 = new Motorbike();
                while (isIDExist(tmp2.getId())) {
                    String id = InputFromCMD.inputString("ID already exist, pLease reinput ID: ", "", "");
                    tmp2.setId(id);
                }
                list.add(tmp2);
                System.out.println("Motorbike added successfully!");
                System.out.println(tmp2.toString());
                break;
        }
    }

    public void addAVehicle(String id, String name, String color, double price, String brand, String type, int yearOfManufact) {
        Vehicle tmp = new Car(id, name, color, price, brand, type, yearOfManufact);
        list.add(tmp);
    }

    public void addAVehicle(String id, String name, String color, double price, String brand, double speed, boolean requireLicense) {
        Vehicle tmp = new Motorbike(id, name, color, price, brand, speed, requireLicense);
        list.add(tmp);
    }

    String key;
    public List<Vehicle> searchVehicle() {
        while (true) {
            key = InputFromCMD.inputString("Find vehicle: ", "", "").toUpperCase();
            ArrayList<String> keyWordList = (ArrayList<String>) Arrays.asList(key.split(" ")).stream().map(x -> x.trim()).filter(x -> !x.equals("")).collect(Collectors.toList());
            List<Vehicle> tmpList = list.stream().filter(x -> {
                List<String> tmp = (ArrayList<String>) keyWordList.clone();
                tmp.retainAll(x.getKeywordsList());
                return tmp.size() != 0;
            }).collect(Collectors.toList());
            if (tmpList.size() > 0) {
                return tmpList;
            } else {
                System.out.println("Nothing found!");
                String choose = InputFromCMD.inputString("Do you want to continue(Y/N): ", "", "");
                while (!choose.equalsIgnoreCase("Y") && !choose.equalsIgnoreCase("N")) {
                    choose = InputFromCMD.inputString("Please try again(Y/N): ", "", "");
                }
                if (choose.equalsIgnoreCase("N")) {
                    return tmpList;
                }
            }
        }
    }

    public List<Vehicle> searchVehicle(String key) {
        ArrayList<String> keyWordList = (ArrayList<String>) Arrays.asList(key.split(" ")).stream().map(x -> x.trim()).filter(x -> !x.equals("")).collect(Collectors.toList());
        List<Vehicle> tmpList = list.stream().filter(x -> {
            List<String> tmp = (ArrayList<String>) keyWordList.clone();
            tmp.retainAll(x.getKeywordsList());
            return tmp.size() != 0;
        }).collect(Collectors.toList());
        return tmpList;
    }

    public void deleteVehicle() {
        List<Vehicle> searchList = searchVehicle();
        if (searchList.size() == 0) {
            System.out.println("Vehicle not found!");
        } else {
            System.out.println("Search result: ");
            for (int i = 1; i <= searchList.size(); i++) {
                System.out.println(i + ") " + searchList.get(i - 1).toString());
            }
        }
        while (searchList.size() > 0) {
            int choose = InputFromCMD.inputInteger("Which do you want to delete(0 to skip): ", "", "Please try again: ", 0, searchList.size());
            if (choose != 0) {
                for (Vehicle x : list) {
                    if (x.getId().equals(searchList.get(choose - 1).getId())) {
                        list.remove(x);
                        searchList.remove(choose - 1);
                        break;
                    }
                }
                System.out.println("Delete successfully!");
                searchList =searchVehicle(key);
                if (searchList.size() != 0) {
                    System.out.println("Search result: ");
                    System.out.printf("   |%-10s|%10s|%20s|%10s|%10s|%15s|%15s|%10s|\n", "VEHICLE", "ID", "NAME", "COLOR", "PRICE", "BRAND", "TYPE/SPEED", "YOM/RL");
                    for (int i = 1; i <= searchList.size(); i++) {
                        System.out.print(i + ") ");
                        searchList.get(i - 1).outputInfo();
                    }
                    String choose1 = InputFromCMD.inputString("Do you want to continue(Y/N): ", "", "");
                    while (!choose1.equalsIgnoreCase("Y") && !choose1.equalsIgnoreCase("N")) {
                        choose1 = InputFromCMD.inputString("Please try again(Y/N): ", "", "");
                    }
                    if (choose1.equalsIgnoreCase("N")) {
                        break;
                    }
                }

            } else break;
        }

    }

    public void updateVehicle(boolean isAdmin) {
        List<Vehicle> searchList = searchVehicle();
        if (searchList.size() == 0) {
            System.out.println("Vehicle not found!");
        } else {
            System.out.println("Search result: ");
            for (int i = 1; i <= searchList.size(); i++) {
                System.out.println(i + ") " + searchList.get(i - 1).toString());
            }
        }
        while (searchList.size() > 0) {
            int choose = InputFromCMD.inputInteger("Which do you want to update(0 to skip): ", "", "Please try again: ", 0, searchList.size());
            if (choose != 0) {
                for (Vehicle x : list) {
                    if (x.getId().equals(searchList.get(choose - 1).getId())) {
                        x.updateInfo(isAdmin);
                        String tmp = x.getId();
                        x.setId("");
                        while (isIDExist(tmp)) {
                            tmp = InputFromCMD.inputString("ID already exist, pLease reinput ID: ", "", "");
                            //if (id.equals(tmp)) break;
                        }
                        x.setId(tmp);
                        x.outputInfo();
                        System.out.println("");
                        break;
                    }
                }
                searchList =searchVehicle(key);
                if (searchList.size() != 0) {
                    System.out.println("Search result: ");
                    System.out.printf("   |%-10s|%10s|%20s|%10s|%10s|%15s|%15s|%10s|\n", "VEHICLE", "ID", "NAME", "COLOR", "PRICE", "BRAND", "TYPE/SPEED", "YOM/RL");

                    for (int i = 1; i <= searchList.size(); i++) {
                        System.out.print(i + ") ");
                        searchList.get(i - 1).outputInfo();
                    }
                    System.out.println("Update successfully!");
                    String choose1 = InputFromCMD.inputString("Do you want to continue(Y/N): ", "", "");
                    while (!choose1.equalsIgnoreCase("Y") && !choose1.equalsIgnoreCase("N")) {
                        choose1 = InputFromCMD.inputString("Please try again(Y/N): ", "", "");
                    }
                    if (choose1.equalsIgnoreCase("N")) {
                        break;
                    }
                }
            } else break;
        }
    }
}
