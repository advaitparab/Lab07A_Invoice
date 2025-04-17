package model;

import java.util.ArrayList;

public class Invoice {
    private ArrayList<LineItem> items = new ArrayList<>();
    private Customer customer;

    public Invoice(Customer customer) {
        this.customer = customer;
    }

    public void addItem(LineItem item) {
        items.add(item);
    }

    public double getTotal() {
        double total = 0;
        for (LineItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public String generateInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== INVOICE ==========\n");
        sb.append(customer.toString()).append("\n-----------------------------\n");

        for (LineItem item : items) {
            sb.append(item.toString()).append("\n");
        }

        sb.append("-----------------------------\n");
        sb.append("TOTAL: $").append(String.format("%.2f", getTotal())).append("\n");
        sb.append("=============================\n");

        return sb.toString();
    }
}
