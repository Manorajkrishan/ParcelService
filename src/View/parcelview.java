package View;
import javax.swing.*;

import Controller.Worker;
import Model.Customer;
import Model.Log;
import Model.Parcel;
import Model.ParcelMap;
import Model.QueueOfCustomers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class parcelview extends JFrame {
    private ParcelMap parcelMap;
    private QueueOfCustomers customerQueue;
    private Worker worker;
    private Log log;

    private JTextArea parcelArea;
    private JTextArea customerArea;
    private JTextArea processArea;

    public parcelview(ParcelMap parcelMap, QueueOfCustomers customerQueue, Worker worker, Log log) {
        this.parcelMap = parcelMap;
        this.customerQueue = customerQueue;
        this.worker = worker;
        this.log = log;

        setTitle("Depot Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel - Display parcels and customers
        JPanel displayPanel = new JPanel(new GridLayout(1, 2));
        parcelArea = new JTextArea(10, 20);
        customerArea = new JTextArea(10, 20);
        displayPanel.add(new JScrollPane(parcelArea));
        displayPanel.add(new JScrollPane(customerArea));
        add(displayPanel, BorderLayout.CENTER);

        // Bottom panel - Processing details
        processArea = new JTextArea(5, 40);
        add(new JScrollPane(processArea), BorderLayout.SOUTH);

        // Buttons
        JButton processButton = new JButton("Process Next Customer");
        JButton clearButton = new JButton("Clear All");

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processNextCustomer();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(processButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.NORTH);

        updateDisplay();
        setVisible(true);
    }

    private void processNextCustomer() {
        if (customerQueue.isEmpty()) {
            processArea.append("No customers in queue.\n");
            return;
        }

        Customer customer = customerQueue.processCustomer();
        String result = worker.processParcel(customer, parcelMap, log);
        processArea.append(result + "\n");
        updateDisplay();
    }

    private void clearAll() {
        customerQueue.clear();
        processArea.setText("");
        updateDisplay();
    }

    private void updateDisplay() {
        parcelArea.setText("Parcels:\n");
        for (Parcel parcel : parcelMap.getAllParcels()) {
            parcelArea.append(parcel.toString() + "\n");
        }

        customerArea.setText("Customers:\n");
        for (Customer customer : customerQueue.getQueue()) {
            customerArea.append(customer.toString() + "\n");
        }
    }
}
