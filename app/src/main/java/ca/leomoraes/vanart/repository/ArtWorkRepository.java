package ca.leomoraes.vanart.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import ca.leomoraes.vanart.dao.AppDatabase;
import ca.leomoraes.vanart.model.ArtWork;

public class ArtWorkRepository {

    private static class SingletonHelper {
        private static final ArtWorkRepository INSTANCE = new ArtWorkRepository();
    }

    public static ArtWorkRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public ArtWorkRepository() {

    }

    public LiveData<List<ArtWork>> getAll(Context context) {
        return AppDatabase.getAppDatabase(context).artWorkDao().getAll();
    }

    public LiveData<ArtWork> getById(Context context, Integer id) {
        return AppDatabase.getAppDatabase(context).artWorkDao().findById(id);
    }

    public LiveData<List<ArtWork>> getByArtist(Context context, Integer id) {
        return AppDatabase.getAppDatabase(context).artWorkDao().getByArtist(id);
    }

    public LiveData<List<ArtWork>> getFavorites(Context context, int[] ids) {
        return AppDatabase.getAppDatabase(context).artWorkDao().findByIds(ids);
    }

    public LiveData<List<ArtWork>> getByNeighbourhood(Context context, String neighbourhood) {
        return AppDatabase.getAppDatabase(context).artWorkDao().findByNeighbourhood(neighbourhood);
    }
}
