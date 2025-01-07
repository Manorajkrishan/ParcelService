package Controller;

import Model.Customer;
import Model.Log;
import Model.Parcel;
import Model.ParcelMap;
import Model.QueueOfCustomers;
import View.parcelview;

public class Manager {
    public static void main(String[] args) {
        ParcelMap parcelMap = new ParcelMap();
        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
        Log log = Log.getInstance();

        // Initialize data
        parcelMap.addParcel(new Parcel("C101", 2, 10.0, "2x5x2"));
        parcelMap.addParcel(new Parcel("C102", 5, 12.5, "3x3x3"));

        javax.swing.SwingUtilities.invokeLater(() -> new parcelview(parcelMap, queueOfCustomers, new Worker(), log));
    }
}
