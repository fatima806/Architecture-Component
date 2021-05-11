package sd.nctr.mvvm.repo;

import android.app.Application;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sd.nctr.mvvm.model.Note;
import sd.nctr.mvvm.repo.database.NoteDatabase;
import sd.nctr.mvvm.repo.database.dao.NoteDao;
import sd.nctr.mvvm.repo.remote.NoteAPI;


/**
 * Created by Fatima .
 */
public class NoteRepository {
    public static final String BASE_URL = "https://tq-notes-api-jkrgrdggbq-el.a.run.app/";
    public static final String API_KEY = "502ce39b-2565-4b32-8257-a2cc20e44468";
    Retrofit retrofit;
    NoteAPI service;
    private NoteDao noteDao;
    LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        service = retrofit.create(NoteAPI.class);

        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public MutableLiveData<List<Note>> getRemoteNotes() {
        final MutableLiveData<List<Note>> data = new MutableLiveData<>();
        service.callNotes(API_KEY).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                for(int i =0;i<response.body().size();i++){
                    insert(new Note(response.body().get(i).getNoteId(),response.body().get(i).getTitle(),response.body().get(i).getCreateDateTime(),response.body().get(i).getEditDateTime()));
                }

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<List<Note>> getAllPosts() {
        return allNotes;
    }

    public void insert(Note note) {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> {
            long id = noteDao.insert(note);
            Log.e(NoteRepository.class.getSimpleName(), "ID : " + id);
        };
        ex.execute(runnable);
    }


}
