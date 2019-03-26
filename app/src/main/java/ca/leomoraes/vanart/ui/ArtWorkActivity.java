package ca.leomoraes.vanart.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.OnClick;
import ca.leomoraes.vanart.R;
import ca.leomoraes.vanart.util.ViewModelFactory;
import ca.leomoraes.vanart.viewModel.ArtWorkDetailViewModel;

public class ArtWorkActivity extends BaseActivity {

    public static String EXTRA_ID = "artwork_id";
    private int artworkId;
    private int artistId;

    @BindView(R.id.artwork_title)
    TextView tvTitle;

    @BindView(R.id.artwork_description)
    TextView tvDescription;

    @BindView(R.id.artwork_neighbourhood)
    TextView tvNeighbourhood;

    @BindView(R.id.artwork_location)
    TextView tvLocation;

    @BindView(R.id.artwork_year)
    TextView tvYear;

    @BindView(R.id.artwork_removed)
    TextView tvRemoved;

    @BindView(R.id.artwork_progress)
    ProgressBar progressBar;

    @BindView(R.id.artwork_scrollview)
    ScrollView scrollView;

    @BindView(R.id.artwork_image)
    ImageView artImage;

    @BindView(R.id.artwork_image_full)
    ImageView artImageFull;

    @BindView(R.id.artwork_btn_artist)
    Button btnArtist;

    @BindView(R.id.artwork_divider)
    View divider;

    @BindView(R.id.artwork_fab)
    FloatingActionButton fab;

    @OnClick(R.id.artwork_fab)
    void fabClick(){
        if(viewModel.getFavorite().getValue()==null)
            viewModel.insertFavorite( artworkId );
        else
            viewModel.deleteFavorite( artworkId );
    }

    private ArtWorkDetailViewModel viewModel;

    void toggleImage(View view){
        if(artImageFull.getVisibility()==View.VISIBLE){
            artImageFull.setVisibility(View.INVISIBLE);
            artImage.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
            btnArtist.setVisibility(View.VISIBLE);
            fab.show();
        }else{
            artImageFull.setVisibility(View.VISIBLE);
            artImage.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.INVISIBLE);
            tvTitle.setVisibility(View.INVISIBLE);
            divider.setVisibility(View.INVISIBLE);
            btnArtist.setVisibility(View.INVISIBLE);
            fab.hide();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null
                && getIntent().getExtras().containsKey(EXTRA_ID))
            artworkId = getIntent().getIntExtra(EXTRA_ID, 0);
        else
            artworkId = 0;

        setupViewModel();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.getExtras() != null
                && intent.getExtras().containsKey(EXTRA_ID)) {
            artworkId = intent.getIntExtra(EXTRA_ID, 0);
            viewModel.setIdParam(artworkId);
        }
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_art_work;
    }

    @Override
    int getTituloActivity() {
        return R.string.app_name;
    }

    private void setupViewModel() {
        progressBar.setVisibility(View.VISIBLE);
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), artworkId)).get(ArtWorkDetailViewModel.class);
        viewModel.getArtWork().observe(this, artwork -> {

            artistId = 0;
            if(artwork!=null) {
                if (artwork.getPhotoURL() != null && !artwork.getPhotoURL().isEmpty()) {
                    Glide
                        .with(this)
                        .load(artwork.getPhotoURL())
                        .apply(new RequestOptions().centerCrop())
                        .into(artImage);

                    Glide
                        .with(this)
                        .load(artwork.getPhotoURL())
                        .apply(new RequestOptions().dontTransform() )
                        .into(artImageFull);
                }

                tvTitle.setText(artwork.getTitleOfWork());
                tvDescription.setText(artwork.getDescriptionOfwork());
                tvLocation.setText(String.format( getResources().getString(R.string.artwork_location), artwork.getSiteName(), artwork.getSiteAddress()));
                tvNeighbourhood.setText(artwork.getNeighbourhood());

                tvRemoved.setVisibility( artwork.getStatus().equalsIgnoreCase("Removed")?View.VISIBLE:View.GONE);
                tvYear.setText(String.valueOf(artwork.getYearOfInstallation()));

                if(artwork.getArtists()!=null && !artwork.getArtists().isEmpty())
                    artistId = Integer.parseInt(artwork.getArtists());
            }

            progressBar.setVisibility(View.GONE);
        });

        viewModel.getFavorite().observe(this, favorite -> {
            if(favorite!=null){
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_full));
            }else{
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_empty));
            }
        });
    }

    @OnClick(R.id.artwork_btn_artist)
    public void openArtWork() {
        Intent intent = new Intent(this, ArtistDetailActivity.class);
        intent.putExtra(ArtistDetailActivity.EXTRA_ID, artistId);
        startActivity( intent );
    }
}