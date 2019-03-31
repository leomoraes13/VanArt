package ca.leomoraes.vanart.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.nitrico.lastadapter.LastAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import ca.leomoraes.vanart.BR;
import ca.leomoraes.vanart.R;
import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.model.Favorite;
import ca.leomoraes.vanart.viewModel.FavoriteViewModel;

public class FavoriteActivity extends BaseActivity implements OnMapReadyCallback {

    @BindView(R.id.favorite_recycler)
    RecyclerView mRecycler;

    @BindView(R.id.favorite_progress)
    ProgressBar progressBar;

    private FavoriteViewModel viewModel;
    private boolean mapReady = false;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupRecycler();
        setupViewModel();
        setupMap();
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_favorite;
    }

    @Override
    int getTituloActivity() {
        return R.string.app_name;
    }


    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.favorite_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        this.googleMap = googleMap;
        updateMap(null);
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(linearLayout);
    }

    private void setupViewModel() {
        progressBar.setVisibility(View.VISIBLE);
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        viewModel.getArtworks().observe(this, artworks -> {

            new LastAdapter(artworks, BR.item)
                    .map(ArtWork.class, R.layout.item_artwork)
                    .into(mRecycler);

            updateMap(artworks);
            progressBar.setVisibility(View.GONE);
        });

        viewModel.getFavorites().observe(this, favorites -> {

            int[] ints = new int[favorites.size()];
            int i=0;
            for (Favorite favorite:favorites) {
                ints[i] = favorite.getRegistryID();
                i++;
            }
            viewModel.setFavorites(ints);
        });
    }

    private void updateMap(List<ArtWork> artworks){
        if(mapReady && artworks!=null && !artworks.isEmpty()) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            // Marker m;
            LatLng location;
            for (ArtWork artWork : artworks) {
                if (artWork.getLongitude()!=null && artWork.getLatitude()!=null) {
                    location = new LatLng( artWork.getLatitude(), artWork.getLongitude());
                    googleMap.addMarker(new MarkerOptions()
                            .position(location)
                            .title(artWork.getTitleOfWork()));
                    // m.setTag();
                    builder.include(location);
                }
            }

            LatLngBounds bounds = builder.build();

            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.20);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));
        }
    }

    public void openArtWork(View view) {
        TextView text = view.findViewById(R.id.tv_artwork_id);

        Intent intent = new Intent(this, ArtWorkActivity.class);
        intent.putExtra(ArtWorkActivity.EXTRA_ID, Integer.parseInt(text.getText().toString()));
        startActivity( intent );
    }

}