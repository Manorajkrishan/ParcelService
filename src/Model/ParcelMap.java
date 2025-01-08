package Model;

import java.util.HashMap;

import java.util.*;

import java.util.*;

public class ParcelMap {
    private Map<String, Parcel> parcelMap = new HashMap<>();

    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getId(), parcel);
    }

    public Parcel findParcel(String id) {
        return parcelMap.get(id); // Returns the parcel with the given ID, or null if not found
    }

    public void removeParcel(String id) {
        parcelMap.remove(id);
    }

    public List<Parcel> getAllParcels() {
        return new ArrayList<>(parcelMap.values());
    }
}

