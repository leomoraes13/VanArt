package ca.leomoraes.vanart.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

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

    @SuppressLint("StaticFieldLeak")
    public void insert(Context context, Favorite favorite) {

        new AsyncTask<Favorite, Void, Void>() {
            @Override
            protected Void doInBackground(Favorite... favorites) {
                AppDatabase.getAppDatabase(context).favoriteDao().insert(favorites[0]);
                return null;
            }
        }.execute(favorite);

    }

    @SuppressLint("StaticFieldLeak")
    public void delete(Context context, Favorite favorite) {

        new AsyncTask<Favorite, Void, Void>() {
            @Override
            protected Void doInBackground(Favorite... favorites) {
                AppDatabase.getAppDatabase(context).favoriteDao().delete(favorites[0]);
                return null;
            }
        }.execute(favorite);

    }
}
