package ca.leomoraes.vanart.data;

import java.util.ArrayList;
import java.util.List;

import ca.leomoraes.vanart.model.Neighbourhood;

public class Cache {
    private static List<Neighbourhood> neighbourhoods = new ArrayList<>();

    public static void clear() {
        neighbourhoods.clear();
    }

    public static List<Neighbourhood> getNeighbourhoods(){
        return neighbourhoods;
    }

    public static void add(String name) {
        Neighbourhood neighbourhood = new Neighbourhood(name);

        if(neighbourhoods.contains(neighbourhood) ){
            int indexOf = neighbourhoods.indexOf(neighbourhood);
            neighbourhoods.get(indexOf).addTotal();
        }else
            neighbourhoods.add(neighbourhood);
    }
}
