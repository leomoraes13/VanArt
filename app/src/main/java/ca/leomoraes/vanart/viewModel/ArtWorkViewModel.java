package ca.leomoraes.vanart.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.repository.ArtWorkRepository;

public class ArtWorkViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = ArtWorkViewModel.class.getSimpleName();

    // List of artWorks
    private LiveData<List<ArtWork>> artWorks;

    public ArtWorkViewModel(@NonNull Application application) {
        super(application);
        artWorks = ArtWorkRepository.getInstance().getAll(application.getBaseContext());
    }

    public LiveData<List<ArtWork>> getAll() {
        return artWorks;
    }
}