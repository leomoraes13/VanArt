package ca.leomoraes.vanart.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ca.leomoraes.vanart.model.ArtWork;
import ca.leomoraes.vanart.model.Artist;
import ca.leomoraes.vanart.model.Favorite;

@Database(entities = {Artist.class, ArtWork.class, Favorite.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ArtistDao artistDao();
    public abstract ArtWorkDao artWorkDao();
    public abstract FavoriteDao favoriteDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "artworkDB")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
