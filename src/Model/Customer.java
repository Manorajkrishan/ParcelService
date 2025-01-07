package Model;

public class Customer {
    private String name;
    private String parcelId;

    public Customer(String name, String parcelId) {
        this.name = name;
        this.parcelId = parcelId;
    }

    public String getName() {
        return name;
    }

    public String getParcelId() {
        return parcelId;
    }

    @Override
    public String toString() {
        return "Customer Name: " + name + ", Parcel ID: " + parcelId;
    }
}
