package sd.nctr.mvvm.repo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import sd.nctr.mvvm.model.Note;
import sd.nctr.mvvm.repo.database.converter.Converters;
import sd.nctr.mvvm.repo.database.dao.NoteDao;

/**
 * Created by Fatima .
 */
@Database(entities = {Note.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
