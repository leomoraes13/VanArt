package ca.leomoraes.vanart.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.leomoraes.vanart.R;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tv_toolbar_titulo)
    TextView tvTituloToolbar;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        ButterKnife.bind(this);

        // Use the Builder class for convenient dialog construction
        builder = new AlertDialog.Builder(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        tvTituloToolbar.setText(getTituloActivity());
    }

    abstract @LayoutRes int getLayoutResId();

    abstract int getTituloActivity();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_artworks:
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_artists:
                intent = new Intent(this, ArtistListActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_favorites:
                Toast.makeText(this, "favorites", Toast.LENGTH_SHORT).show();
/*                intent = new Intent(this, FavoriteListActivity.class);
                startActivity(intent);*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialogMessage(Integer resId){
        this.showDialog( getResources().getString(resId) );
    }

    public void showDialog(String msg){
        builder.setMessage(msg)
                .setPositiveButton(R.string.dialog_ok, (dialog, id) -> dialog.dismiss());

        // Create the AlertDialog
        builder.create();
    }
}
