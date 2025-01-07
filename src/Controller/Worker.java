package Controller;

import Model.Customer;
import Model.Log;
import Model.Parcel;
import Model.ParcelMap;

public class Worker {
    public double calculateFee(Parcel parcel) {
        double baseFee = 5.0;
        double additionalFee = 0.1 * (parcel.getDaysInDepot() + parcel.getWeight());
        return baseFee + additionalFee;
    }

    public String processParcel(Customer customer, ParcelMap parcelMap, Log log) {
        Parcel parcel = parcelMap.findParcel(customer.getParcelId());
        if (parcel != null) {
            double fee = calculateFee(parcel);
            log.addEntry("Processed Customer: " + customer.getName() + 
                         ", Parcel ID: " + parcel.getId() + 
                         ", Fee: £" + fee);
            parcelMap.removeParcel(parcel.getId());
            return "Processed Parcel ID: " + parcel.getId() + " for " + customer.getName() + ". Fee: £" + fee;
        } else {
            log.addEntry("Parcel not found for Customer: " + customer.getName());
            return "Parcel not found for " + customer.getName();
        }
    }
}
