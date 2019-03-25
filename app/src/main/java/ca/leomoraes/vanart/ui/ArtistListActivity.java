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

import butterknife.BindView;
import ca.leomoraes.vanart.BR;
import ca.leomoraes.vanart.R;
import ca.leomoraes.vanart.model.Artist;
import ca.leomoraes.vanart.viewModel.ArtistViewModel;

public class ArtistListActivity extends BaseActivity {

    @BindView(R.id.artist_list_recycler)
    RecyclerView mRecycler;

    @BindView(R.id.artist_list_progress)
    ProgressBar progressBar;

    private ArtistViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupRecycler();
        setupViewModel();
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_artist_list;
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
        viewModel = ViewModelProviders.of(this).get(ArtistViewModel.class);
        viewModel.getAll().observe(this, artists -> {

            new LastAdapter(artists, BR.item)
                    .map(Artist.class, R.layout.item_artist)
                    .into(mRecycler);

            progressBar.setVisibility(View.GONE);
        });
    }

    public void openArtist(View view) {
        TextView text = view.findViewById(R.id.tv_artist_id);

        Intent intent = new Intent(this, ArtistDetailActivity.class);
        intent.putExtra(ArtistDetailActivity.EXTRA_ID, Integer.parseInt(text.getText().toString()));
        startActivity( intent );
    }
}
