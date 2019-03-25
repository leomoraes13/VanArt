package ca.leomoraes.vanart.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import ca.leomoraes.vanart.dao.AppDatabase;
import ca.leomoraes.vanart.model.Artist;

public class ArtistRepository {

    private static class SingletonHelper {
        private static final ArtistRepository INSTANCE = new ArtistRepository();
    }

    public static ArtistRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public ArtistRepository() {

    }

    public LiveData<List<Artist>> getAll(Context context) {
        return AppDatabase.getAppDatabase(context).artistDao().getAll();
    }

    public LiveData<Artist> getById(Context context, Integer id) {
        return AppDatabase.getAppDatabase(context).artistDao().findById(id);
    }
}
