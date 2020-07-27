package xyz.purposeless.meniny.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {NameEntry.class}, version = 1)
@TypeConverters({RoomConverters.class})
public abstract class NameDatabase extends RoomDatabase {
    public abstract NameDao nameDao();

    private static volatile NameDatabase nameDatabase;

    static NameDatabase getNameDatabase(final Context context) {
        if (nameDatabase == null) {
            nameDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    NameDatabase.class, "names").build();
        }

        return nameDatabase;
    }
}
