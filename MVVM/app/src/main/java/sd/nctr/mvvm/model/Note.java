package sd.nctr.mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by Fatima Abdalla on 5/9/2021.
 */
@Entity(tableName = "note_table",indices = {@Index(value = {"noteId"},
        unique = true)})
public class Note {


    @PrimaryKey(autoGenerate = true)
    private int id;


    @SerializedName("noteID")
    private String noteId;


    @SerializedName("noteTitle")
    private String title;

    @SerializedName("createDateTime")
    private String createDateTime;

    @SerializedName("latestEditDateTime")
    private String editDateTime;


    public Note(String noteId, String title, String createDateTime, String editDateTime) {
        this.noteId = noteId;
        this.title = title;
        this.createDateTime = createDateTime;
        this.editDateTime = editDateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public String getEditDateTime() {
        return editDateTime;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setEditDateTime(String editDateTime) {
        this.editDateTime = editDateTime;
    }
}
