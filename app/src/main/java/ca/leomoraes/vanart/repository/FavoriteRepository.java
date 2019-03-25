package ca.leomoraes.vanart.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import ca.leomoraes.vanart.dao.AppDatabase;
import ca.leomoraes.vanart.model.Favorite;

public class FavoriteRepository {

    private static class SingletonHelper {
        private static final FavoriteRepository INSTANCE = new FavoriteRepository();
    }

    public static FavoriteRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public FavoriteRepository() {

    }

    public LiveData<List<Favorite>> getAll(Context context) {
        return AppDatabase.getAppDatabase(context).favoriteDao().getAll();
    }

    public LiveData<Favorite> getById(Context context, Integer id) {
        return AppDatabase.getAppDatabase(context).favoriteDao().findById(id);
    }
}
