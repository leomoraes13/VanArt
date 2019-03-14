package ca.leomoraes.vanart.model;

import com.google.gson.annotations.SerializedName;

public class ArtistResponse {

    @SerializedName("type")
    private String type;

    @SerializedName("geometry")
    private String geometry;

    @SerializedName("properties")
    private Artist artist;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}