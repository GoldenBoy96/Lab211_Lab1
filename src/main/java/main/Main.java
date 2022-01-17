package main;

import login.Login;
import service.Service;
import util.InputFromCMD;
import vehicle.Vehicle;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("You must login first!");
        Login login = new Login();
        login.login();

        Service service = new Service();
//        service.addAVehicle("SE1000", "Winner X", "red", 46090, "Honda", 120, true );
//        service.addAVehicle("SE1001", "CBR 605R 2021", "black", 253990, "Honda", 200, true );
//        service.addAVehicle("SE1002", "LEAD 125", "gray", 38990, "Honda", 100, true );
//        service.addAVehicle("SE1003", "SH 350i", "white", 145990, "Honda", 120, true );
//        service.addAVehicle("SE1004", "CBR 150R", "blue", 70990, "Honda", 180, true );
//        service.addAVehicle("SE1005", "Kymco 50cc", "red", 16800, "Visar", 100, false );
//        service.addAVehicle("SE1006", "Giorno 50cc", "pink", 28500, "Honda", 100, false );
//        service.addAVehicle("SE1007", "SH 125i", "yellow", 71790, "Honda", 120, true );
//        service.addAVehicle("SE1008", "Attila 50cc", "gray", 25700, "SYM", 120, false );
//        service.addAVehicle("SE1009", "CBR Fireblade", "red", 949000, "Honda", 200, true );
//        service.addAVehicle("AI1001", "Vios E", "white", 478000, "Toyota", "Sedan", 2021);
//        service.addAVehicle("AI1002", "Lx600", "black", 9800000, "Lexus", "Sedan", 2022);
//        service.addAVehicle("AI1003", "Benz E200", "gray", 1650000, "Mercedes", "Sedan", 2018);

        int choose = -1;
        while (true) {
            service.printMenu();
            choose = service.choose();
            switch (choose) {
                case 1:
                    service.showTheList();
                    break;
                case 2:
                    service.showTheListDecendingByPrice();
                    break;
                case 3:
                    service.addAVehicle();
                    service.isSaved = false;
                    break;
                case 4:
                    List<Vehicle> tmp = service.searchVehicle();
                    System.out.println("Search list:");
                    for (Vehicle x: tmp) {
                        System.out.println(x.toString());
                    }
                    break;
                case 5:
                    service.updateVehicle(login.isAdmin());
                    service.isSaved = false;
                    break;
                case 6:
                    service.deleteVehicle();
                    service.isSaved = false;
                    break;
                case 7:
                    service.openFile();
                    service.isSaved = true;
                    break;
                case 8:
                    service.save();
                    break;
                case 0:
                    if (!service.isSaved) {
                        System.out.println("Do you want to save?\n1) Yes\n2) No\n3) Cancel");
                        int choose1 = InputFromCMD.inputInteger("Your choice: ", "", "", 1, 3);
                        if (choose1 == 1) {
                            service.save();
                            System.exit(0);
                        } else if (choose1 == 2) {
                            System.exit(0);
                        }
                    } else {
                        System.exit(0);
                    }
                    break;
            }
            System.gc();
            System.out.print("Press Enter to continue!");
            String tmp = SC.nextLine();
        }
    }
}
