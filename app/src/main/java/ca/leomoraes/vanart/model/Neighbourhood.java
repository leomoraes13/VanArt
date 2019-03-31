package ca.leomoraes.vanart.model;

import com.google.android.gms.maps.model.LatLng;

public class Neighbourhood {
    private String name;
    private int total;
    private LatLng location;

    public Neighbourhood(String name, int total) {
        this.name = name;
        this.total = total;
    }

    public Neighbourhood(String name) {
        this.name = name;
        this.total = 1;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTotal() {
        this.total += 1;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Neighbourhood.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Neighbourhood other = (Neighbourhood) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }

        return true;
    }
}
