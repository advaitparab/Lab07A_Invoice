package gui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class InvoiceFrame extends JFrame {
    private JTextField nameField, streetField, cityField, stateField, zipField;
    private JTextField productField, priceField, quantityField;
    private JTextArea displayArea;
    private Invoice invoice;

    public InvoiceFrame() {
        setTitle("Invoice Generator");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(9, 2));

        nameField = new JTextField();
        streetField = new JTextField();
        cityField = new JTextField();
        stateField = new JTextField();
        zipField = new JTextField();
        productField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        formPanel.add(new JLabel("Customer Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Street:"));
        formPanel.add(streetField);
        formPanel.add(new JLabel("City:"));
        formPanel.add(cityField);
        formPanel.add(new JLabel("State:"));
        formPanel.add(stateField);
        formPanel.add(new JLabel("Zip:"));
        formPanel.add(zipField);
        formPanel.add(new JLabel("Product Name:"));
        formPanel.add(productField);
        formPanel.add(new JLabel("Unit Price:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(quantityField);

        add(formPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JButton addButton = new JButton("Add Item");
        JButton printButton = new JButton("Print Invoice");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(printButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener((ActionEvent e) -> {
            if (invoice == null) {
                Address addr = new Address(streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText());
                Customer cust = new Customer(nameField.getText(), addr);
                invoice = new Invoice(cust);
            }

            Product prod = new Product(productField.getText(), Double.parseDouble(priceField.getText()));
            int qty = Integer.parseInt(quantityField.getText());
            invoice.addItem(new LineItem(prod, qty));
            displayArea.append(qty + "x " + prod.getName() + " added to invoice.\n");

            productField.setText("");
            priceField.setText("");
            quantityField.setText("");
        });

        printButton.addActionListener((ActionEvent e) -> {
            displayArea.setText(invoice.generateInvoice());
        });

        setVisible(true);
    }
}
