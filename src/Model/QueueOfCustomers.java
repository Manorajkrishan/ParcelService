package Model;


import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> customerQueue = new LinkedList<>();

    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
    }

    public Customer processCustomer() {
        return customerQueue.poll(); // Retrieves and removes the head of the queue
    }

    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

    public void clear() {
        customerQueue.clear();
    }

    public Queue<Customer> getQueue() {
        return new LinkedList<>(customerQueue);
    }
}
