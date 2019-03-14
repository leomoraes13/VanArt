package ca.leomoraes.vanart.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "artist")
public class Artist {

    @PrimaryKey
    @SerializedName("ARTISTID")
    private Integer artistiId;

    @SerializedName("FIRSTNAME")
    private String firstName;

    @SerializedName("LASTNAME")
    private String lastName;

    @SerializedName("COUNTRY")
    private String country;

    @SerializedName("WEBSITE")
    private String website;

    @SerializedName("BIOGRAPHY")
    private String biography;

    @SerializedName("Photo")
    private String photo;

    @SerializedName("PhotoCredit")
    private String photoCredit;

    @SerializedName("ArtistURL")
    private String artistURL;


    public Integer getArtistiId() {
        return artistiId;
    }

    public void setArtistiId(Integer artistiId) {
        this.artistiId = artistiId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhotoCredit() {
        return photoCredit;
    }

    public void setPhotoCredit(String photoCredit) {
        this.photoCredit = photoCredit;
    }

    public String getArtistURL() {
        return artistURL;
    }

    public void setArtistURL(String artistURL) {
        this.artistURL = artistURL;
    }
}
