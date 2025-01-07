package Model;

import java.util.HashMap;

import java.util.*;

public class ParcelMap {
    private Map<String, Parcel> parcels = new HashMap<>();

    public void addParcel(String id, Parcel parcel) {
        parcels.put(id, parcel);
    }

    public Parcel getParcel(String id) {
        return parcels.get(id);
    }

    public void removeParcel(String id) {
        parcels.remove(id);
    }

    public List<Parcel> getAllParcels() {
        return new ArrayList<>(parcels.values());
    }

    public List<Parcel> getSortedParcels() {
        List<Parcel> sortedParcels = new ArrayList<>(parcels.values());
        sortedParcels.sort(Comparator.comparingDouble(Parcel::getWeight));
        return sortedParcels;
    }
}
