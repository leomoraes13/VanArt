package ca.leomoraes.vanart.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.repository.ArtWorkRepository;

public class ArtWorkViewModel extends AndroidViewModel {

    // List of artWorks
    private LiveData<List<ArtWork>> artWorks;

    public ArtWorkViewModel(@NonNull Application application) {
        super(application);
        artWorks = ArtWorkRepository.getInstance().getAll(application.getBaseContext());

        // artWorks = ArtWorkRepository.getInstance().getByNeighbourhood(application.getBaseContext(), "Downtown");

/*        int[] ids = {518,519,520, 528, 539};
        artWorks = ArtWorkRepository.getInstance().getArtworks(application.getBaseContext(), ids);*/
    }

    public LiveData<List<ArtWork>> getAll() {
        return artWorks;
    }
}