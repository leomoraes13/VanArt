package ca.leomoraes.vanart.service;

import android.content.Context;

import java.util.List;

import ca.leomoraes.vanart.R;
import ca.leomoraes.vanart.model.ArtWorkResponse;
import ca.leomoraes.vanart.model.ArtistResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;
    private ApiService apiService;
    private static RetrofitService service;

    public static RetrofitService getInstance(Context context){
        if (service == null) {
            service = new RetrofitService(context);
        }
        return service;
    }

    public RetrofitService(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.apiary_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void getArtists(Callback<List<ArtistResponse>> callback){
        Call<List<ArtistResponse>> jsonCall = apiService.getArtists();
        jsonCall.enqueue(callback);
    }

    public void getArtWorks(Callback<List<ArtWorkResponse>> callback){
        Call<List<ArtWorkResponse>> jsonCall = apiService.getArtWorks();
        jsonCall.enqueue(callback);
    }
}
