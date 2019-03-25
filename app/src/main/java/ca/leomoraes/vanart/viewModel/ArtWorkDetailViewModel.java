package ca.leomoraes.vanart.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.support.annotation.NonNull;

import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.repository.ArtWorkRepository;

public class ArtWorkDetailViewModel extends AndroidViewModel {
    private Context mContext;

    private final MutableLiveData<Integer> idParam = new MutableLiveData();

    // List of artWorks
    private final LiveData<ArtWork> artWorks =
            Transformations.switchMap(idParam, (type) -> ArtWorkRepository.getInstance().getById(mContext, type));

    public ArtWorkDetailViewModel(@NonNull Application application, Integer id) {
        super(application);
        mContext = application.getBaseContext();
        setIdParam(id);
    }

    public void setIdParam(int id) {
        idParam.setValue(id);
    }

    public LiveData<ArtWork> getArtWork() {
        return artWorks;
    }
}