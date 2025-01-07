package Model;

public class Parcel {
    private String id;
    private int daysInDepot;
    private double weight;
    private String dimensions;

    public Parcel(String id, int daysInDepot, double weight, String dimensions) {
        this.id = id;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.dimensions = dimensions;
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

    public String getDimensions() {
        return dimensions;
    }

    @Override
    public String toString() {
        return id + ", " + daysInDepot + ", " + weight + "kg, " + dimensions;
    }
}
