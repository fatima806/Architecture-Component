package sd.nctr.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import sd.nctr.mvvm.model.Note;

/**
 * Created by Fatima.
 */
class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textViewTiltle.setText(currentNote.getTitle());
        holder.textViewCreateDate.setText(currentNote.getCreateDateTime());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }
    public Note getNoteAt(int position) {
        return this.notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView textViewTiltle, textViewCreateDate;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTiltle = itemView.findViewById(R.id.text_view_title);
            textViewCreateDate = itemView.findViewById(R.id.text_view_create_date);
        }
    }
}
