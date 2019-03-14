package ca.leomoraes.vanart.service;

import java.util.List;

import ca.leomoraes.vanart.model.ArtWorkResponse;
import ca.leomoraes.vanart.model.ArtistResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/artist")
    Call<List<ArtistResponse>> getArtists();

    @GET("/publicArt")
    Call<List<ArtWorkResponse>> getArtWorks();
}
