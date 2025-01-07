package Controller;

import Model.Customer;
import Model.Log;
import Model.Parcel;
import Model.ParcelMap;

public class Worker {
    public double calculateFee(Parcel parcel) {
        double baseFee = 5.0;
        double sizeFactor = 0.1 * (parcel.getDaysInDepot() + parcel.getWeight());
        return baseFee + sizeFactor;
    }

    public void processCustomer(Customer customer, ParcelMap parcelMap, Log log) {
        Parcel parcel = parcelMap.findParcel(customer.getParcelId());
        if (parcel != null) {
            double fee = calculateFee(parcel);
            log.addEntry("Processed: " + customer.getName() + ", Fee: £" + fee);
            parcelMap.getParcels().remove(parcel.getId());
        } else {
            log.addEntry("Parcel not found for: " + customer.getName());
        }
    }
}
