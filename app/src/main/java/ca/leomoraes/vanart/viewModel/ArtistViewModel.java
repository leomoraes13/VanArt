package ca.leomoraes.vanart.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ca.leomoraes.vanart.model.Artist;
import ca.leomoraes.vanart.repository.ArtistRepository;

public class ArtistViewModel extends AndroidViewModel {
    // List of artists
    private LiveData<List<Artist>> artists;

    public ArtistViewModel(@NonNull Application application) {
        super(application);
        artists = ArtistRepository.getInstance().getAll(application.getBaseContext());
    }

    public LiveData<List<Artist>> getAll() {
        return artists;
    }
}
