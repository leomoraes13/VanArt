package ca.leomoraes.vanart.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.nitrico.lastadapter.LastAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import ca.leomoraes.vanart.BR;
import ca.leomoraes.vanart.R;
import ca.leomoraes.vanart.data.Cache;
import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.model.Favorite;
import ca.leomoraes.vanart.model.Neighbourhood;
import ca.leomoraes.vanart.viewModel.ArtWorkViewModel;
import ca.leomoraes.vanart.viewModel.FavoriteViewModel;
import ca.leomoraes.vanart.widget.AppWidget;

public class HomeActivity extends BaseActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    @BindView(R.id.home_recycler)
    RecyclerView mRecycler;

    @BindView(R.id.home_progress)
    ProgressBar progressBar;

    @BindView(R.id.home_title)
    TextView title;

    @BindView(R.id.home_close)
    ImageView closeButton;

    private ArtWorkViewModel viewModel;
    private FavoriteViewModel favoriteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupMap();
        setupRecycler();
        setupViewModel();
    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);
        List<Neighbourhood> neighbourhoods = Cache.getNeighbourhoods();
        if(neighbourhoods!=null && !neighbourhoods.isEmpty()) {

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            Marker m;
            for (Neighbourhood neighbourhood:neighbourhoods) {
                if(neighbourhood.getTotal()!=0 && neighbourhood.getLocation()!=null) {
                    m = googleMap.addMarker(new MarkerOptions()
                            .position(neighbourhood.getLocation())
                            .title(neighbourhood.getName() + " (" + neighbourhood.getTotal() + ")"));
                    m.setTag(neighbourhood.getName());
                    builder.include( neighbourhood.getLocation() );
                    // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(neighbourhood.getLocation(), 15));
                }
            }

            LatLngBounds bounds = builder.build();

            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.20);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        String name = (String) marker.getTag();
        if (name != null) {
            progressBar.setVisibility(View.VISIBLE);
            viewModel.setNeighbourhood(name);
            title.setText(name);
            closeButton.setVisibility(View.VISIBLE);
        }
        return false;
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    int getTituloActivity() {
        return R.string.app_name;
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(linearLayout);
    }

    private void setupViewModel() {
        progressBar.setVisibility(View.VISIBLE);
        viewModel = ViewModelProviders.of(this).get(ArtWorkViewModel.class);
        viewModel.getAll().observe(this, artworks -> {
            new LastAdapter(artworks, BR.item)
                    .map(ArtWork.class, R.layout.item_artwork)
                    .into(mRecycler);

            // AppWidget.mFavoritesArtworks = artworks.subList(10, 20);
            progressBar.setVisibility(View.GONE);
        });

        // Setting the favorites widget
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        favoriteViewModel.getArtworks().observe(this, artworks -> AppWidget.mFavoritesArtworks = artworks);
        favoriteViewModel.getFavorites().observe(this, favorites -> {
            if(favorites!=null) {
                int[] ints = new int[favorites.size()];
                int i = 0;
                for (Favorite favorite : favorites) {
                    ints[i] = favorite.getRegistryID();
                    i++;
                }
                favoriteViewModel.setFavorites(ints);
            }
        });

        this.closeNeighbourhood();
    }

    public void openArtWork(View view) {
        TextView text = view.findViewById(R.id.tv_artwork_id);

        Intent intent = new Intent(this, ArtWorkActivity.class);
        intent.putExtra(ArtWorkActivity.EXTRA_ID, Integer.parseInt(text.getText().toString()));
        startActivity( intent );
    }

    @OnClick(R.id.home_close)
    public void closeNeighbourhood(){
        title.setText(R.string.main_title_all);
        closeButton.setVisibility(View.INVISIBLE);
        viewModel.setNeighbourhood(null);
    }
}
