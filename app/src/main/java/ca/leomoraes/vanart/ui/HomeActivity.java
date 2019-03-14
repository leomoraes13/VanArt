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
import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.viewModel.ArtWorkViewModel;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_recycler)
    RecyclerView mRecycler;

    @BindView(R.id.home_progress)
    ProgressBar progressBar;

    private ArtWorkViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupRecycler();
        setupViewModel();
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_home;
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

            progressBar.setVisibility(View.GONE);
        });
    }

    public void openArtWork(View view) {
        TextView text = view.findViewById(R.id.tv_artwork_id);

        Intent intent = new Intent(this, ArtWorkActivity.class);
        intent.putExtra(ArtWorkActivity.EXTRA_ID, Integer.parseInt(text.getText().toString()));
        startActivity( intent );
    }
}
