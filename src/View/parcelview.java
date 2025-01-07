package View;
import javax.swing.*;

import Model.Customer;
import Model.Parcel;

import java.awt.*;
import java.util.LinkedList;

public class parcelview {
	private JFrame frame;
    private JTextArea parcelsArea;
    private JTextArea customersArea;
    private JTextArea logArea;

    public parcelview() {
        frame = new JFrame("Depot Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1));

        parcelsArea = new JTextArea();
        customersArea = new JTextArea();
        logArea = new JTextArea();

        mainPanel.add(new JScrollPane(parcelsArea));
        mainPanel.add(new JScrollPane(customersArea));
        mainPanel.add(new JScrollPane(logArea));

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void displayParcels(LinkedList<Parcel> parcels) {
        parcelsArea.setText("Parcels:\n");
        for (Parcel parcel : parcels) {
            parcelsArea.append(parcel.toString() + "\n");
        }
    }

    public void displayCustomers(LinkedList<Customer> customers) {
        customersArea.setText("Customers:\n");
        for (Customer customer : customers) {
            customersArea.append(customer.toString() + "\n");
        }
    }

    public void displayLog(String log) {
        logArea.setText(log);
    }

}
