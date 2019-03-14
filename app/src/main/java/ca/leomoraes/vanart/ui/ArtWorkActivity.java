package ca.leomoraes.vanart.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import ca.leomoraes.vanart.R;
import ca.leomoraes.vanart.data.Cache;
import ca.leomoraes.vanart.model.Neighbourhood;

public class ArtWorkActivity extends BaseActivity {

    public static String EXTRA_ID = "artwork_id";
    private int artworkId;

    @BindView(R.id.textview)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null
                && getIntent().getExtras().containsKey(EXTRA_ID))
            artworkId = getIntent().getIntExtra(EXTRA_ID, 0);
        else
            artworkId = 0;


        text.setText("");
        for (Neighbourhood neighbourhood: Cache.getNeighbourhoods()) {
            text.append(neighbourhood.getName());
            text.append(": "+neighbourhood.getTotal());
            text.append("\n");
        }

        setupViewModel();
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_art_work;
    }

    private void setupViewModel() {

    }
}
