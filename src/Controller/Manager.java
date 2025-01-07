package Controller;

import Model.Customer;
import Model.Log;
import Model.Parcel;
import Model.ParcelMap;
import Model.QueueOfCustomers;
import View.parcelview;

import java.io.*;
import javax.swing.*;

public class Manager {
    private ParcelMap parcelMap = new ParcelMap();
    private QueueOfCustomers customerQueue = new QueueOfCustomers();
    private Log log = Log.getInstance();

    public void loadParcels(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                int daysInDepot = Integer.parseInt(data[1]);
                double weight = Double.parseDouble(data[2]);
                String destination = data[3];
                Parcel parcel = new Parcel(id, daysInDepot, weight, destination);
                parcelMap.addParcel(parcel);
            }
        } catch (IOException e) {
            System.out.println("Error reading parcel file: " + e.getMessage());
        }
    }

    public void loadCustomers(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String parcelId = data[1];
                Customer customer = new Customer(name, parcelId);
                customerQueue.addCustomer(customer);
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        Worker worker = new Worker();

        // Load parcels and customers
        manager.loadParcels("parcels.txt");
        manager.loadCustomers("customers.txt");

        // Start the GUI
        SwingUtilities.invokeLater(() -> {
            new parcelview(manager.parcelMap, manager.customerQueue, worker, manager.log);
        });
    }
}
