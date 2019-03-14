package ca.leomoraes.vanart.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ca.leomoraes.vanart.model.ArtWork;

@Dao
public interface ArtWorkDao {


    @Query("SELECT * FROM artWork ORDER BY titleOfWork ASC")
    LiveData<List<ArtWork>> getAll();

    @Query("SELECT * FROM artWork where registryID = :id ")
    LiveData<ArtWork> findById(Integer id);

    @Query("SELECT COUNT(*) from artWork")
    int countUsers();

    @Insert
    void insertAll(ArtWork... artists);

    @Query("DELETE FROM artWork")
    void deleteAll();
}