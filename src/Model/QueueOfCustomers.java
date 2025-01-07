package Model;

import java.util.LinkedList;

public class QueueOfCustomers {
    private LinkedList<Customer> queue;

    public QueueOfCustomers() {
        queue = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        queue.add(customer);
    }

    public Customer processCustomer() {
        return queue.poll();
    }

    public LinkedList<Customer> getQueue() {
        return queue;
    }
}