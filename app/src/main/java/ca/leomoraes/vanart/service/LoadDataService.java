package ca.leomoraes.vanart.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.List;

import ca.leomoraes.vanart.dao.AppDatabase;
import ca.leomoraes.vanart.data.Cache;
import ca.leomoraes.vanart.model.ArtWorkResponse;
import ca.leomoraes.vanart.model.ArtistResponse;
import ca.leomoraes.vanart.ui.SplashActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadDataService extends IntentService {
    private static final String TAG = "LoadDataService";

    public LoadDataService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        intent.setAction(SplashActivity.FILTER_ACTION_KEY);

        Cache.clear();
        RetrofitService
            .getInstance(LoadDataService.this)
            .getArtists(
                    new Callback<List<ArtistResponse>>() {
                        @Override
                        public void onResponse(Call<List<ArtistResponse>> call, Response<List<ArtistResponse>> response) {
                            if (response.isSuccessful()) {
                                Log.d(TAG, "" + response.body().get(0).getArtist().getFirstName());

                                AppDatabase.getAppDatabase(LoadDataService.this).artistDao().deleteAll();
                                for (ArtistResponse artistResponse:response.body()) {
                                    AppDatabase.getAppDatabase(LoadDataService.this).artistDao().insertAll( artistResponse.getArtist() );
                                }

                                RetrofitService
                                    .getInstance(LoadDataService.this)
                                    .getArtWorks(
                                            new Callback<List<ArtWorkResponse>>() {
                                                @Override
                                                public void onResponse(Call<List<ArtWorkResponse>> call, Response<List<ArtWorkResponse>> response) {
                                                    if (response.isSuccessful()) {
                                                        Log.d(TAG, "" + response.body().get(0).getArtWork().getTitleOfWork());

                                                        AppDatabase.getAppDatabase(LoadDataService.this).artWorkDao().deleteAll();
                                                        for (ArtWorkResponse artWorkResponse:response.body()) {
                                                            AppDatabase.getAppDatabase(LoadDataService.this).artWorkDao().insertAll( artWorkResponse.getArtWork() );
                                                            Cache.add( artWorkResponse.getArtWork() );
                                                        }

                                                        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra("broadcastMessage", true));
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<List<ArtWorkResponse>> call, Throwable t) {
                                                    Log.d(TAG, "Erro: " + t.getMessage());
                                                }
                                            }
                                    );
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ArtistResponse>> call, Throwable t) {
                            Log.d(TAG, "Erro: " + t.getMessage());
                        }
                    }
            );
    }
}
