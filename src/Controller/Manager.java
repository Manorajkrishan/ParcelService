package Controller;

import Model.Customer;
import Model.Log;
import Model.Parcel;
import Model.ParcelMap;
import Model.QueueOfCustomers;

public class Manager {
    public static void main(String[] args) {
        ParcelMap parcelMap = new ParcelMap();
        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
        Log log = Log.getInstance();

        // Initialize data (in a real app, read from files)
        parcelMap.addParcel(new Parcel("C101", 2, 10.0, "2x5x2"));
        queueOfCustomers.addCustomer(new Customer(1, "John Brown", "C101"));

        Worker worker = new Worker();

        // Process customers
        while (!queueOfCustomers.getQueue().isEmpty()) {
            Customer customer = queueOfCustomers.processCustomer();
            worker.processCustomer(customer, parcelMap, log);
        }

        log.writeLogToFile("log.txt");
    }
}
