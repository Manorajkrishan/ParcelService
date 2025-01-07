package Model;

public class Customer {
    private int sequenceNumber;
    private String name;
    private String parcelId;

    public Customer(int sequenceNumber, String name, String parcelId) {
        this.sequenceNumber = sequenceNumber;
        this.name = name;
        this.parcelId = parcelId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public String getName() {
        return name;
    }

    public String getParcelId() {
        return parcelId;
    }

    @Override
    public String toString() {
        return sequenceNumber + ", " + name + ", Parcel ID: " + parcelId;
    }
}
