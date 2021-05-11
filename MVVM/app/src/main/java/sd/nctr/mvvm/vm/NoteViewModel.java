package sd.nctr.mvvm.vm;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import sd.nctr.mvvm.model.Note;
import sd.nctr.mvvm.repo.NoteRepository;

/**
 * Created by Fatima.
 */
public class NoteViewModel extends AndroidViewModel {

    private LiveData<List<Note>> allNotes;
    private NoteRepository repository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        repository.getRemoteNotes();
        allNotes = repository.getAllPosts();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

}
