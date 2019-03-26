package ca.leomoraes.vanart.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.model.Favorite;
import ca.leomoraes.vanart.repository.ArtWorkRepository;
import ca.leomoraes.vanart.repository.FavoriteRepository;

public class FavoriteViewModel extends AndroidViewModel {
    private Context mContext;

    // List of favorites
    private LiveData<List<Favorite>> favorites;

    // List of artWorks
    private final LiveData<List<ArtWork>> artWorks =
            Transformations.switchMap(favorites, favorites -> {
                int[] ints = new int[favorites.size()];
                int i=0;
                for (Favorite favorite:favorites) {
                    ints[i] = favorite.getRegistryID();
                    i++;
                }
                return ArtWorkRepository.getInstance().getFavorites(mContext, ints);
            });

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getBaseContext();
        favorites = FavoriteRepository.getInstance().getAll(mContext);
    }

    public LiveData<List<ArtWork>> getFavorites() {
        return artWorks;
    }
}