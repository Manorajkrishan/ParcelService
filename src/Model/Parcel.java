package Model;

public class Parcel {
    private String id;
    private int daysInDepot;
    private double weight;
    private String destination;

    public Parcel(String id, int daysInDepot, double weight, String destination) {
        this.id = id;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public double getWeight() {
        return weight;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return id + ", " + daysInDepot + " days, " + weight + "kg, " + destination;
    }
}
