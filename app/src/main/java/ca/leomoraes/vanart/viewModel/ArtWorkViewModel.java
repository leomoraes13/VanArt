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
import ca.leomoraes.vanart.repository.ArtWorkRepository;

public class ArtWorkViewModel extends AndroidViewModel {
    private Context mContext;

    private MutableLiveData<String> neighbourhood = new MutableLiveData();

    // List of artWorks
    private final LiveData<List<ArtWork>> artWorks =
            Transformations.switchMap(neighbourhood, (name) -> {
                if(name==null)
                    return ArtWorkRepository.getInstance().getAll(mContext);
                return ArtWorkRepository.getInstance().getByNeighbourhood(mContext, name);
            });

    public ArtWorkViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getBaseContext();
    }

    public LiveData<List<ArtWork>> getAll() {
        return artWorks;
    }

    public void setNeighbourhood(String neighbourhood){
        this.neighbourhood.setValue( neighbourhood );
    }
}