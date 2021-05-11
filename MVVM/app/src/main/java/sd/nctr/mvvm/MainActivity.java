package sd.nctr.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import sd.nctr.mvvm.databinding.ActivityMainBinding;

import sd.nctr.mvvm.vm.NoteViewModel;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    NoteViewModel noteViewModel;
    NoteAdapter noteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main, null, false);
        setContentView(mainBinding.getRoot());

        noteViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);

        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter();
        mainBinding.recyclerView.setAdapter(noteAdapter);

        noteViewModel.getAllNotes().observe(this, notes -> {
            mainBinding.myProgressBar.setVisibility(View.GONE);
            noteAdapter.setNotes(notes);
        });


        mainBinding.themeToggle.setOnClickListener(view -> {
            int currentNightMode = MainActivity.this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });
    }
}
