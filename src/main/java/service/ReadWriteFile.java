package service;

import vehicle.Vehicle;

import java.io.*;
import java.util.List;

public class ReadWriteFile <T> {
    public boolean writeFile(List<T> list, String filePath) {
        new File(filePath);
        try {
            FileOutputStream f = new FileOutputStream(filePath);
            ObjectOutputStream oStream = new ObjectOutputStream(f);
            oStream.writeObject(list);
            oStream.close();
        } catch (IOException e) {
            System.out.println("Error Write file!");
            return false;
        }
        System.out.println("File saved!");
        return true;
    }

    public List<T> readFile(String filePath) {
        List<T> tmp = null;
        try {
            FileInputStream f = new FileInputStream(filePath);
            ObjectInputStream inStream = new ObjectInputStream(f);
            tmp = (List<T>) inStream.readObject();
            inStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return tmp;
    }

}
