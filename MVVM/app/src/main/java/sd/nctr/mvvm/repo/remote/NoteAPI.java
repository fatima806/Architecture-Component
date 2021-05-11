package sd.nctr.mvvm.repo.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import sd.nctr.mvvm.model.Note;

/**
 * Created by Fatima Abdalla on 5/9/2021.
 */
public interface NoteAPI {

    @GET("notes")
    Call<List<Note>> callNotes(@Header("apiKey")String header);
}
