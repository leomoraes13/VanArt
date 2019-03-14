package ca.leomoraes.vanart.model;

import com.google.gson.annotations.SerializedName;

public class ArtWorkResponse {

    @SerializedName("type")
    private String type;

    @SerializedName("properties")
    private ArtWork artWork;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArtWork getArtWork() {
        return artWork;
    }

    public void setArtWork(ArtWork artWork) {
        this.artWork = artWork;
    }
}