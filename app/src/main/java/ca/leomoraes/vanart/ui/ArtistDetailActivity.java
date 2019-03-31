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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.nitrico.lastadapter.LastAdapter;

import butterknife.BindView;
import ca.leomoraes.vanart.BR;
import ca.leomoraes.vanart.R;
import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.util.ViewModelFactory;
import ca.leomoraes.vanart.viewModel.ArtistDetailViewModel;

public class ArtistDetailActivity extends BaseActivity {

    public static String EXTRA_ID = "artist_id";
    private int artistId;

    @BindView(R.id.artist_name)
    TextView tvName;

    @BindView(R.id.artist_country)
    TextView tvCountry;

    @BindView(R.id.artist_website)
    TextView tvWebsite;

    @BindView(R.id.artist_biography)
    TextView tvBiography;

    @BindView(R.id.artist_photo)
    ImageView artistPhoto;

    @BindView(R.id.artist_recycler)
    RecyclerView mRecycler;

    @BindView(R.id.artist_progress)
    ProgressBar progressBar;

    private ArtistDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null
                && getIntent().getExtras().containsKey(EXTRA_ID))
            artistId = getIntent().getIntExtra(EXTRA_ID, 0);
        else
            artistId = 0;

        setupRecycler();
        setupViewModel();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.getExtras() != null
                && intent.getExtras().containsKey(EXTRA_ID)) {
            artistId = intent.getIntExtra(EXTRA_ID, 0);
            viewModel.setIdParam(artistId);
        }
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_artist_detail;
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
        viewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), artistId)).get(ArtistDetailViewModel.class);
        viewModel.getArtist().observe(this, artist -> {
            if(artist!=null) {
                if (artist.getPhoto() != null && !artist.getPhoto().isEmpty()) {
                    Glide
                            .with(this)
                            .load(artist.getPhoto())
                            .apply(new RequestOptions().centerCrop())
                            .into(artistPhoto);
                }
                tvName.setText(artist.getFullName());
                tvCountry.setText(artist.getCountry());
                tvWebsite.setText(artist.getWebsite());
                tvBiography.setText(artist.getBiography());
            }else{
                this.showDialogMessage(R.string.dialog_erro_artist);
            }

            progressBar.setVisibility(View.GONE);
        });

        viewModel.getArtWorkList().observe(this, artWorks -> new LastAdapter(artWorks, BR.item)
                .map(ArtWork.class, R.layout.item_artwork)
                .into(mRecycler));
    }

    public void openArtWork(View view) {
        TextView text = view.findViewById(R.id.tv_artwork_id);

        Intent intent = new Intent(this, ArtWorkActivity.class);
        intent.putExtra(ArtWorkActivity.EXTRA_ID, Integer.parseInt(text.getText().toString()));
        startActivity( intent );
    }
}