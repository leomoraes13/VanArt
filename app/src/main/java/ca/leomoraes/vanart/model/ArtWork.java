package ca.leomoraes.vanart.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "artWork")
public class ArtWork {

    @PrimaryKey
    @SerializedName("RegistryID")
    private Integer registryID;
    
    @SerializedName("TitleOfWork")
    private String titleOfWork;
    
    @SerializedName("YearOfInstallation")
    private Integer yearOfInstallation;
    
    @SerializedName("Status")
    private String status;
    
    @SerializedName("DescriptionOfwork")
    private String descriptionOfwork;
    
    @SerializedName("ArtistProjectStatement")
    private String artistProjectStatement;
    
    @SerializedName("SiteName")
    private String siteName;
    
    @SerializedName("SiteAddress")
    private String siteAddress;
    
    @SerializedName("Neighbourhood")
    private String neighbourhood;
    
    @SerializedName("Latitude")
    private Double latitude;
    
    @SerializedName("Longitude")
    private Double longitude;
    
    @SerializedName("LocationOnsite")
    private String locationOnsite;
    
    @SerializedName("Type")
    private String type;
    
    @SerializedName("PrimaryMaterial")
    private String primaryMaterial;
    
    @SerializedName("Ownership")
    private String ownership;
    
    @SerializedName("URL")
    private String url;
    
    @SerializedName("PhotoURL")
    private String photoURL;
    
    @SerializedName("PhotoCredits")
    private String photoCredits;
    
    @SerializedName("Artists")
    private String artists;


    public Integer getRegistryID() {
        return registryID;
    }

    public String getRegistryIdFmt() {
        return ""+registryID;
    }

    public void setRegistryID(Integer registryID) {
        this.registryID = registryID;
    }

    public String getTitleOfWork() {
        return titleOfWork;
    }

    public void setTitleOfWork(String titleOfWork) {
        this.titleOfWork = titleOfWork;
    }

    public Integer getYearOfInstallation() {
        return yearOfInstallation;
    }

    public void setYearOfInstallation(Integer yearOfInstallation) {
        this.yearOfInstallation = yearOfInstallation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescriptionOfwork() {
        return descriptionOfwork;
    }

    public void setDescriptionOfwork(String descriptionOfwork) {
        this.descriptionOfwork = descriptionOfwork;
    }

    public String getArtistProjectStatement() {
        return artistProjectStatement;
    }

    public void setArtistProjectStatement(String artistProjectStatement) {
        this.artistProjectStatement = artistProjectStatement;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getNeighbourhood() {
        return neighbourhood==null?"N/A":neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocationOnsite() {
        return locationOnsite;
    }

    public void setLocationOnsite(String locationOnsite) {
        this.locationOnsite = locationOnsite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrimaryMaterial() {
        return primaryMaterial;
    }

    public void setPrimaryMaterial(String primaryMaterial) {
        this.primaryMaterial = primaryMaterial;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getPhotoCredits() {
        return photoCredits;
    }

    public void setPhotoCredits(String photoCredits) {
        this.photoCredits = photoCredits;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }
}