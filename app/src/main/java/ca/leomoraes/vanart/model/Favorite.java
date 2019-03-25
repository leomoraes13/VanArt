package ca.leomoraes.vanart.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "favorite")
public class Favorite {

    @PrimaryKey
    @SerializedName("RegistryID")
    private Integer registryID;

    public Integer getRegistryID() {
        return registryID;
    }
    public void setRegistryID(Integer registryID) {
        this.registryID = registryID;
    }
}
