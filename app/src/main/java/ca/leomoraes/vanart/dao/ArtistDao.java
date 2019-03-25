package ca.leomoraes.vanart.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ca.leomoraes.vanart.model.Artist;

@Dao
public interface ArtistDao {

    @Query("SELECT * FROM artist ORDER BY lastName ASC")
    LiveData<List<Artist>> getAll();

    @Query("SELECT * FROM artist where artistiId = :id ")
    LiveData<Artist> findById(Integer id);

    @Query("SELECT COUNT(*) from artist")
    int countUsers();

    @Insert
    void insertAll(Artist... artists);

    @Query("DELETE FROM artist")
    void deleteAll();
}