package ca.leomoraes.vanart.util;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import ca.leomoraes.vanart.viewModel.ArtWorkDetailViewModel;
import ca.leomoraes.vanart.viewModel.ArtistDetailViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private Integer id;


    public ViewModelFactory(Application application, Integer id) {
        mApplication = application;
        this.id = id;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArtWorkDetailViewModel.class)){
            return (T) new ArtWorkDetailViewModel(mApplication, id);
        } else if (modelClass.isAssignableFrom(ArtistDetailViewModel.class)){
            return (T) new ArtistDetailViewModel(mApplication, id);
        }
        return null;
    }
}
