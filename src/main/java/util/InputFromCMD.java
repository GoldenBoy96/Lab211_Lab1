package util;

import java.util.Scanner;

public class InputFromCMD {

    private static final Scanner SC = new Scanner(System.in);

    public static int inputInteger(String inputMsg, String outputMsg, String reInputMsg) {
        int input = 0;

        System.out.print(inputMsg);
        while (true) {
            try {
                String tmp = SC.nextLine();
                if (tmp.equals("")) return -1;
                input = Integer.parseInt(tmp);
                break;
            } catch (NumberFormatException e) {
                System.out.print(reInputMsg);
            }
        }
        outputMsg = outputMsg.trim();
        if (!outputMsg.equals("")) {
            System.out.println(outputMsg);
        }
        return input;
    }

    public static int inputInteger(String inputMsg, String outputMsg, String reInputMsg, int bottomValue, int topValue) {
        int input = 0;
        if (bottomValue > topValue) {
            int t = bottomValue;
            bottomValue = topValue;
            topValue = t;
        }
        System.out.print(inputMsg);
        while (true) {
            try {
                String tmp = SC.nextLine();
                if (tmp.equals("")) return -1;
                input = Integer.parseInt(tmp);
                if (bottomValue != topValue) { //default infinity
                    if (input >= bottomValue && input <= topValue) {
                        break;
                    } else {
                        System.out.print("Please input an integer between " + bottomValue + " and " + topValue + ": ");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.print(reInputMsg);
            }
        }
        outputMsg = outputMsg.trim();
        if (!outputMsg.equals("")) {
            System.out.println(outputMsg);
        }
        return input;
    }

    public static double inputDouble(String inputMsg, String outputMsg, String reInputMsg) {
        double input = 0;

        System.out.print(inputMsg);
        while (true) {
            try {
                String tmp = SC.nextLine();
                if (tmp.equals("")) return -1;
                input = Double.parseDouble(tmp);
                break;
            } catch (NumberFormatException e) {
                System.out.print(reInputMsg);
            }
        }
        outputMsg = outputMsg.trim();
        if (!outputMsg.equals("")) {
            System.out.println(outputMsg);
        }
        return input;
    }

    public static double inputDouble(String inputMsg, String outputMsg, String reInputMsg, double bottomValue, double topValue) {
        double input = 0;
        if (bottomValue > topValue) {
            double t = bottomValue;
            bottomValue = topValue;
            topValue = t;
        }
        System.out.print(inputMsg);
        while (true) {
            try {
                String tmp = SC.nextLine();
                if (tmp.equals("")) return -1;
                input = Double.parseDouble(tmp);
                if (bottomValue != topValue) { //default infinity
                    if (input >= bottomValue && input <= topValue) {
                        break;
                    } else {
                        System.out.print("Please input an Double between " + bottomValue + " and " + topValue + ": ");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.print(reInputMsg);
            }
        }
        outputMsg = outputMsg.trim();
        if (!outputMsg.equals("")) {
            System.out.println(outputMsg);
        }
        return input;
    }

    public static String inputString(String inputMsg, String outputMsg, String reInputMsg) {
        String input = null;
        System.out.print(inputMsg);
        while (true) {
            try {
                input = SC.nextLine();
                break;
            } catch (NumberFormatException e) {
                System.out.print(reInputMsg);
            }
        }
        outputMsg = outputMsg.trim();
        if (!outputMsg.equals("")) {
            System.out.println(outputMsg);
        }
        return input.trim();
    }

}