package ca.leomoraes.vanart.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ca.leomoraes.vanart.model.Favorite;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite ORDER BY registryID ASC")
    LiveData<List<Favorite>> getAll();

    @Query("SELECT * FROM favorite WHERE registryID = :id ")
    LiveData<Favorite> findById(Integer id);

    @Insert
    void insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);
}
