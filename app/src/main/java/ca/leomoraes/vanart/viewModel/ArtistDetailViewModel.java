package ca.leomoraes.vanart.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.model.Artist;
import ca.leomoraes.vanart.repository.ArtWorkRepository;
import ca.leomoraes.vanart.repository.ArtistRepository;

public class ArtistDetailViewModel extends AndroidViewModel {
    private Context mContext;

    private final MutableLiveData<Integer> idParam = new MutableLiveData();

    // Artist
    private final LiveData<Artist> artist =
            Transformations.switchMap(idParam, (type) -> ArtistRepository.getInstance().getById(mContext, type));

    // List of Artworks
    private final LiveData<List<ArtWork>> artWorkList =
            Transformations.switchMap(idParam, (type) -> ArtWorkRepository.getInstance().getByArtist(mContext, type));

    public ArtistDetailViewModel(@NonNull Application application, Integer id) {
        super(application);
        mContext = application.getBaseContext();
        setIdParam(id);
    }

    public void setIdParam(int id) {
        idParam.setValue(id);
    }

    public LiveData<Artist> getArtist() {
        return artist;
    }
    public LiveData<List<ArtWork>> getArtWorkList() {
        return artWorkList;
    }
}
