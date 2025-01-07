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

public class parcelview extends JFrame {
    private ParcelMap parcelMap;
    private QueueOfCustomers queueOfCustomers;
    private Worker worker;
    private Log log;

    private JTextArea parcelArea;
    private JTextArea customerArea;
    private JTextArea processArea;
    private JTextField nameField;
    private JTextField parcelIdField;
    private JTextField parcelWeightField;
    private JTextField parcelDimensionsField;
    private JTextField parcelDaysField;

    public parcelview(ParcelMap parcelMap, QueueOfCustomers queueOfCustomers, Worker worker, Log log) {
        this.parcelMap = parcelMap;
        this.queueOfCustomers = queueOfCustomers;
        this.worker = worker;
        this.log = log;

        setTitle("Depot Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel - Display data
        JPanel displayPanel = new JPanel(new GridLayout(1, 2));
        parcelArea = new JTextArea(10, 20);
        customerArea = new JTextArea(10, 20);
        displayPanel.add(new JScrollPane(parcelArea));
        displayPanel.add(new JScrollPane(customerArea));
        add(displayPanel, BorderLayout.CENTER);

        // Bottom panel - Processing details
        processArea = new JTextArea(5, 40);
        add(new JScrollPane(processArea), BorderLayout.SOUTH);

        // Input and buttons
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        nameField = new JTextField();
        parcelIdField = new JTextField();
        parcelWeightField = new JTextField();
        parcelDimensionsField = new JTextField();
        parcelDaysField = new JTextField();

        inputPanel.add(new JLabel("Customer Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Parcel ID:"));
        inputPanel.add(parcelIdField);
        inputPanel.add(new JLabel("Parcel Weight (kg):"));
        inputPanel.add(parcelWeightField);
        inputPanel.add(new JLabel("Parcel Dimensions:"));
        inputPanel.add(parcelDimensionsField);
        inputPanel.add(new JLabel("Days in Depot:"));
        inputPanel.add(parcelDaysField);

        JButton addCustomerButton = new JButton("Add Customer");
        JButton addParcelButton = new JButton("Add Parcel");
        JButton processButton = new JButton("Process Customer");

        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });

        addParcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addParcel();
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processCustomer();
            }
        });

        inputPanel.add(addCustomerButton);
        inputPanel.add(addParcelButton);
        inputPanel.add(processButton);

        add(inputPanel, BorderLayout.NORTH);

        updateDisplay();
        setVisible(true);
    }

    private void addCustomer() {
        String name = nameField.getText().trim();
        String parcelId = parcelIdField.getText().trim();

        if (name.isEmpty() || parcelId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Customer Name and Parcel ID are required!");
            return;
        }

        if (!parcelMap.getParcels().containsKey(parcelId)) {
            JOptionPane.showMessageDialog(this, "Parcel ID does not exist!");
            return;
        }

        Customer customer = new Customer(queueOfCustomers.getQueue().size() + 1, name, parcelId);
        queueOfCustomers.addCustomer(customer);
        nameField.setText("");
        parcelIdField.setText("");
        updateDisplay();
    }

    private void addParcel() {
        String id = parcelIdField.getText().trim();
        String weightText = parcelWeightField.getText().trim();
        String dimensions = parcelDimensionsField.getText().trim();
        String daysText = parcelDaysField.getText().trim();

        if (id.isEmpty() || weightText.isEmpty() || dimensions.isEmpty() || daysText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All parcel fields are required!");
            return;
        }

        try {
            double weight = Double.parseDouble(weightText);
            int daysInDepot = Integer.parseInt(daysText);
            Parcel parcel = new Parcel(id, daysInDepot, weight, dimensions);
            parcelMap.addParcel(parcel);
            parcelIdField.setText("");
            parcelWeightField.setText("");
            parcelDimensionsField.setText("");
            parcelDaysField.setText("");
            updateDisplay();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Weight and Days in Depot must be numbers!");
        }
    }

    private void processCustomer() {
        Customer customer = queueOfCustomers.processCustomer();
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "No customers in the queue!");
            return;
        }

        Parcel parcel = parcelMap.findParcel(customer.getParcelId());
        if (parcel == null) {
            processArea.setText("Parcel not found for: " + customer.getName());
            log.addEntry("Parcel not found for: " + customer.getName());
            return;
        }

        double fee = worker.calculateFee(parcel);
        processArea.setText("Processed Customer: " + customer.getName() + "\nParcel ID: " + parcel.getId() +
                            "\nFee: £" + fee);
        log.addEntry("Processed Customer: " + customer.getName() + ", Fee: £" + fee);
        parcelMap.getParcels().remove(parcel.getId());
        updateDisplay();
    }

    private void updateDisplay() {
        // Update parcels
        parcelArea.setText("Parcels:\n");
        for (Parcel parcel : parcelMap.getParcels().values()) {
            parcelArea.append(parcel.toString() + "\n");
        }

        // Update customers
        customerArea.setText("Customers in Queue:\n");
        for (Customer customer : queueOfCustomers.getQueue()) {
            customerArea.append(customer.toString() + "\n");
        }
    }
}