package ca.leomoraes.vanart.data;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.model.Neighbourhood;

public class Cache {
    private static List<Neighbourhood> neighbourhoods = new ArrayList<>();

    public static void clear() {
        neighbourhoods.clear();
    }

    public static List<Neighbourhood> getNeighbourhoods(){
        return neighbourhoods;
    }

    public static void add(ArtWork artWork) {
        Neighbourhood neighbourhood = new Neighbourhood(artWork.getNeighbourhood());

        if(neighbourhoods.contains(neighbourhood) ){
            int indexOf = neighbourhoods.indexOf(neighbourhood);
            neighbourhoods.get(indexOf).addTotal();
        }else {
            if(artWork.getLatitude()!=null && artWork.getLongitude()!=null) {
                neighbourhood.setLocation( new LatLng(artWork.getLatitude(), artWork.getLongitude()) );
            }
            neighbourhoods.add(neighbourhood);
        }
    }
}
